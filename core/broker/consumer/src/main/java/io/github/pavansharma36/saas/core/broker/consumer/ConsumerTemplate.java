package io.github.pavansharma36.saas.core.broker.consumer;


import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.core.common.context.providers.MDCContextProvider;
import io.github.pavansharma36.core.common.context.providers.ThreadLocalContextProviders;
import io.github.pavansharma36.saas.core.broker.common.BrokerUtils;
import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageSerializablePayload;
import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenExecutor;
import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenResponse;
import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenerConsumer;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.ConsumerFactory;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.PollExecutor;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.PollResponse;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.PollerConsumer;
import io.github.pavansharma36.saas.core.broker.consumer.processor.MessageProcessor;
import io.github.pavansharma36.saas.core.broker.consumer.processor.ProcessInstruction;
import io.github.pavansharma36.saas.core.broker.producer.ProducerTemplate;
import io.github.pavansharma36.saas.utils.Constants;
import io.github.pavansharma36.saas.utils.Utils;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import io.github.pavansharma36.saas.utils.poll.DelayedLogEmitter;
import io.github.pavansharma36.saas.utils.poll.FixedDelayedLogEmitter;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerTemplate {

  private final Map<String, ConsumerFactory<?, ?>> consumerFactoryMap;
  private final Map<String, ProducerTemplate> producerTemplateMap;
  private final Map<String, MessageProcessor> messageProcessorMap;

  public ConsumerTemplate(List<ConsumerFactory<?, ?>> pollerConsumerFactories,
                          List<ProducerTemplate> producerTemplates,
                          List<MessageProcessor> messageProcessors) {
    consumerFactoryMap =
        pollerConsumerFactories.stream().collect(Collectors.toMap(ConsumerFactory::type,
            f -> f));
    producerTemplateMap = producerTemplates.stream().collect(Collectors.toMap(
        ProducerTemplate::type,
        p -> p));

    messageProcessorMap =
        messageProcessors.stream()
            .collect(Collectors.toMap(m -> m.messageType().getName(), m -> m));
  }

  private <T extends ListenResponse> void listen(Queue queue, ListenerConsumer<T> c) {
    ListenExecutor<T> executor = new ListenExecutor<>(queue, c, getMessageHandler(queue));
    executor.start();
  }

  private <T extends PollResponse> void poll(Queue queue, PollerConsumer<T> c) {
    int threadCount = Config.getInt(String.format("%s.poller.thread.count", queue.getName()), 1);
    log.info("Starting poller thread for queue {} with thread count {}", queue.getName(),
        threadCount);
    for (int i = 0; i < threadCount; i++) {
      PollExecutor<T> executor = new PollExecutor<>(queue, c, getMessageHandler(queue));
      executor.start();
    }
  }

  public void consume(ApplicationContext context, Map<String, List<Queue>> typeQueueMap) {

    for (Map.Entry<String, List<Queue>> typeQueues : typeQueueMap.entrySet()) {
      boolean usePollerForType =
          Config.getBoolean(String.format("%s.queue.type.poller.enabled", typeQueues.getKey()),
              Config.getBoolean("consumer.poller.enabled", false));

      for (Queue queue : typeQueues.getValue()) {
        ConsumerFactory<?, ?> consumerFactory =
            Optional.ofNullable(consumerFactoryMap.get(typeQueues.getKey()))
                .orElseThrow(() -> new ServerRuntimeException(
                    String.format("ConsumerFactory for type %s is not found",
                        typeQueues.getKey())));

        boolean usePollerForQueue =
            Config.getBoolean(String.format("%s.queue.poller.enabled", queue.getName()),
                usePollerForType);

        if (usePollerForQueue) {
          log.info("Using poller for queue {}:{}", typeQueues.getKey(), queue.getName());

          PollerConsumer<?> poller = consumerFactory.createPollerConsumer(queue);
          poll(queue, poller);
        } else {
          log.info("Using listener for queue {}:{}", typeQueues.getKey(), queue.getName());

          ListenerConsumer<?> listener = consumerFactory.createListenerConsumer(queue);
          listen(queue, listener);
        }
      }
    }
  }

  private Consumer<byte[]> getMessageHandler(Queue queue) {
    DelayedLogEmitter logEmitter = new FixedDelayedLogEmitter(Duration.ofSeconds(30L), log);
    return data -> {
      log.info("Received message on queue {}", queue);
      MessageSerializablePayload payload;
      try {
        payload = BrokerUtils.deserialize(data);
      } catch (Throwable e) {
        log.error("Error while deserializing message, will ignore message processing {}",
            e.getMessage(), e);
        return;
      }

      try {
        MDCContextProvider.put(Constants.MESSAGE_ID_MDC_KEY,
            payload.getMessageId() == null ? Utils.randomRequestId() : payload.getMessageId());
        MDCContextProvider.put(Constants.MESSAGE_TYPE_MDC_KEY, payload.getMessageType());
        log.info("Successfully deserialized payload: {}", payload);

        Date expireAt = payload.getMessageDto().getExpireAt();
        if (expireAt != null && System.currentTimeMillis() > expireAt.getTime()) {
          log.warn("Message already expired, skipping execution {}", payload);
          return;
        }
        log.info("Setting all context from payload.");
        ThreadLocalContextProviders.set(payload.getContextMap());
        log.info("Done setting all context from payload");

        MessageProcessor processor =
            Optional.ofNullable(messageProcessorMap.get(payload.getMessageType())).orElseThrow(
                () -> new ServerRuntimeException(
                    "Message processor not found : " + payload.getMessageType()));
        log.info("Found message processor {}", processor.getClass());
        if (BrokerUtils.isQueueBlocked(queue, payload.getPriority(), logEmitter)) {
          redispatch(queue, payload);
        }
        ProcessInstruction instruction = processor.canProcess(payload);

        switch (instruction.getInstruction()) {
          case SKIP -> {
            log.warn("Message instruction was to skip {}", payload);
          }
          case PROCESS -> {
            log.info("Instruction was to process message, starting to process");
            processor.process(instruction, payload.getMessageDto());
            log.info("Successfully processed message");
          }
          case DELAY -> {
            log.warn("Cannot process {} at the moment, redispatching", payload);
            redispatch(queue, payload);
          }
          default -> {
            log.error("Unsupported instruction for message {}: {}", instruction.getInstruction(),
                payload);
          }
        }
      } catch (Exception e) {
        log.error("Error while processing message {}: {}", e.getMessage(), payload, e);
      } finally {
        ThreadLocalContextProviders.clearAll();
      }
    };
  }

  private void redispatch(Queue queue, MessageSerializablePayload payload) {
    int warnOnCount = Config.getInt("message.redispatch.warn.count", 10);
    if (payload.getDispatchedCount() > warnOnCount) {
      log.warn("Message redispatched more than {} times {}: {}", warnOnCount,
          payload.getDispatchedCount(), payload);
    }

    DelayedQueue nextQueue =
        BrokerUtils.nextDelayedQueue(queue, payload.getLastDelayedQueue());

    payload.setLastDispatchedAt(new Date());
    payload.setLastDelayedQueue(nextQueue);
    payload.setDispatchedCount(payload.getDispatchedCount() + 1);
    log.info("Redispatching message to {}:{}", queue, nextQueue);

    Optional.ofNullable(producerTemplateMap.get(queue.type())).orElseThrow(() ->
            new ServerRuntimeException("ProducerTemplate required for : " + queue.getName()))
        .produce(queue, nextQueue, BrokerUtils.serialize(payload));
    log.info("Successfully redispatched message");
  }

}
