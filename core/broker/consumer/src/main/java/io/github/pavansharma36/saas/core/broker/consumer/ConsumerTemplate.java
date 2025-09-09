package io.github.pavansharma36.saas.core.broker.consumer;


import io.github.pavansharma36.saas.core.broker.common.BrokerUtils;
import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageSerializablePayload;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageStatus;
import io.github.pavansharma36.saas.core.broker.common.dao.MessageInfoDao;
import io.github.pavansharma36.saas.core.broker.common.dao.model.MessageInfo;
import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenExecutor;
import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenResponse;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.ConsumerFactory;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.PollExecutor;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.PollResponse;
import io.github.pavansharma36.saas.core.broker.consumer.processor.MessageProcessor;
import io.github.pavansharma36.saas.core.broker.consumer.processor.ProcessInstruction;
import io.github.pavansharma36.saas.core.broker.consumer.processor.ProcessorNotFoundException;
import io.github.pavansharma36.saas.core.broker.producer.ProducerTemplate;
import io.github.pavansharma36.saas.core.common.config.Config;
import io.github.pavansharma36.saas.core.common.context.providers.MDCContextProvider;
import io.github.pavansharma36.saas.core.common.context.providers.ThreadLocalContextProviders;
import io.github.pavansharma36.saas.core.common.validation.ServerRuntimeException;
import io.github.pavansharma36.saas.utils.Constants;
import io.github.pavansharma36.saas.utils.Utils;
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

  private final MessageInfoDao messageInfoDao;
  private final Map<String, ConsumerFactory<?, ?>> consumerFactoryMap;
  private final Map<String, ProducerTemplate> producerTemplateMap;
  private final Map<String, MessageProcessor> messageProcessorMap;

  public ConsumerTemplate(MessageInfoDao messageInfoDao,
                          List<ConsumerFactory<?, ?>> pollerConsumerFactories,
                          List<ProducerTemplate> producerTemplates,
                          List<MessageProcessor> messageProcessors) {
    this.messageInfoDao = messageInfoDao;
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

  private <L extends ListenResponse, P extends PollResponse> void listen(Queue queue,
                                                                         ConsumerFactory<L, P> c) {
    int threadCount = Config.getInt(String.format("%s.listener.thread.count", queue.getName()),
        Config.getInt("listener.thread.count", 1));
    log.info("Starting listener thread for queue {} with thread count {}", queue.getName(),
        threadCount);
    for (int i = 0; i < threadCount; i++) {
      ListenExecutor<L> executor =
          new ListenExecutor<>(queue, c.createListenerConsumer(),
              getMessageHandler(queue));
      executor.start();
    }
  }

  private <L extends ListenResponse, P extends PollResponse> void poll(Queue queue,
                                                                       ConsumerFactory<L, P> c) {
    int threadCount = Config.getInt(String.format("%s.poller.thread.count", queue.getName()),
        Config.getInt("poller.thread.count", 1));
    log.info("Starting poller thread for queue {} with thread count {}", queue.getName(),
        threadCount);
    for (int i = 0; i < threadCount; i++) {
      PollExecutor<P> executor =
          new PollExecutor<>(queue, c.createPollerConsumer(), getMessageHandler(queue));
      executor.start();
    }
  }

  public void consume(ApplicationContext context, Map<String, List<Queue>> typeQueueMap) {

    for (Map.Entry<String, List<Queue>> typeQueues : typeQueueMap.entrySet()) {
      boolean usePollerForType =
          Config.getBoolean(
              String.format("%s.queue.type.consumer.poller.enabled", typeQueues.getKey()),
              Config.getBoolean("consumer.poller.enabled", false));

      for (Queue queue : typeQueues.getValue()) {
        ConsumerFactory<?, ?> consumerFactory =
            Optional.ofNullable(consumerFactoryMap.get(typeQueues.getKey()))
                .orElseThrow(() -> new ServerRuntimeException(
                    String.format("ConsumerFactory for type %s is not found",
                        typeQueues.getKey())));

        boolean usePollerForQueue =
            Config.getBoolean(String.format("%s.queue.consumer.poller.enabled", queue.getName()),
                usePollerForType);

        if (usePollerForQueue) {
          log.info("Using consumerFactor for queue {}:{}", typeQueues.getKey(), queue.getName());
          poll(queue, consumerFactory);
        } else {
          log.info("Using listener for queue {}:{}", typeQueues.getKey(), queue.getName());
          listen(queue, consumerFactory);
        }
      }
    }
  }

  private Consumer<byte[]> getMessageHandler(Queue queue) {
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

        log.info("Setting all context from payload.");
        ThreadLocalContextProviders.set(payload.getContextMap());
        log.info("Done setting all context from payload");

        MessageProcessor processor =
            Optional.ofNullable(messageProcessorMap.get(payload.getMessageType())).orElseThrow(
                () -> new ProcessorNotFoundException(
                    "Message processor not found : " + payload.getMessageType()));
        log.info("Found message processor {}", processor.getClass());

        ProcessInstruction instruction = processor.canProcess(queue, payload);

        switch (instruction.getInstruction()) {
          case SKIP -> log.warn("Message instruction was to skip {}", payload);
          case PROCESS -> {
            log.info("Instruction was to process message, starting to process");
            processor.process(instruction, payload.getMessageDto());
            log.info("Successfully processed message");
          }
          case DELAY -> {
            log.warn("Cannot process {} at the moment, redispatching", payload);
            redispatch(queue, instruction.getPayload());
          }
          default ->
              log.error("Unsupported instruction for message {}: {}", instruction.getInstruction(),
                  payload);
        }
      } catch (ProcessorNotFoundException e) {
        if (payload.getMessageId() != null) {
          Optional<MessageInfo> om = messageInfoDao.findById(payload.getMessageId());
          if (om.isPresent() && BrokerUtils.isOwner(om.get())) {
            BrokerUtils.completeMessage(messageInfoDao, om.get(), MessageStatus.FAILED, e);
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
        .produce(queue, nextQueue, payload, BrokerUtils::serialize);
    log.info("Successfully redispatched message");
  }

}
