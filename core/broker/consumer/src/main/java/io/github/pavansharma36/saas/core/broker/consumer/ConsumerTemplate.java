package io.github.pavansharma36.saas.core.broker.consumer;


import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.consumer.api.ConsumerLifeCycle;
import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenResponse;
import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenerConsumer;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.PollResponse;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.PollerConsumer;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

@Slf4j
public class ConsumerTemplate {

  public static void consume(ApplicationContext context, Map<String, List<Queue>> typeQueueMap) {
    Map<String, ListenerConsumer<?>> listeners = getTypeListenerMap(context);
    Map<String, PollerConsumer<?>> pollers = getTypePollerMap(context);

    for (Map.Entry<String, List<Queue>> typeQueues : typeQueueMap.entrySet()) {
      boolean usePollerForType =
          Config.getBoolean(String.format("%s.queue.type.poller.enabled", typeQueues.getKey()),
              false);

      for (Queue queue : typeQueues.getValue()) {
        boolean usePollerForQueue =
            Config.getBoolean(String.format("%s.queue.poller.enabled", queue.getName()),
                usePollerForType);

        if (usePollerForQueue) {
          log.info("Using poller for queue {}:{}", typeQueues.getKey(), queue.getName());

          PollerConsumer<?> poller = pollers.get(typeQueues.getKey());
          if (poller != null) {
            poll(queue, poller);
          } else {
            throw new ServerRuntimeException(
                String.format("Poller for type %s is not found", typeQueues.getKey()));
          }
        } else {
          log.info("Using listener for queue {}:{}", typeQueues.getKey(), queue.getName());

          ListenerConsumer<?> listener = listeners.get(typeQueues.getKey());
          if (listener != null) {
            listen(queue, listener);
          } else {
            throw new ServerRuntimeException(
                String.format("Listener for type %s is not found", typeQueues.getKey()));
          }
        }
      }
    }
  }

  private static Map<String, ListenerConsumer<?>> getTypeListenerMap(ApplicationContext context) {
    Map<String, ListenerConsumer> consumerTemplates =
        context.getBeansOfType(ListenerConsumer.class);
    return consumerTemplates.values().stream()
        .collect(Collectors.toMap(ConsumerLifeCycle::type, k -> k));
  }

  private static Map<String, PollerConsumer<?>> getTypePollerMap(
      ApplicationContext context) {
    Map<String, PollerConsumer> consumerTemplates =
        context.getBeansOfType(PollerConsumer.class);
    return consumerTemplates.values().stream()
        .collect(Collectors.toMap(ConsumerLifeCycle::type, k -> k));
  }

  private static <T extends ListenResponse> void listen(Queue queue, ListenerConsumer<T> c) {

  }

  private static <T extends PollResponse> void poll(Queue queue, PollerConsumer<T> c) {

  }

}
