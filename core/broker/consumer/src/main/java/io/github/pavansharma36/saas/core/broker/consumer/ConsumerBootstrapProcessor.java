package io.github.pavansharma36.saas.core.broker.consumer;

import io.github.pavansharma36.saas.core.common.context.providers.MDCContextProvider;
import io.github.pavansharma36.saas.core.common.context.providers.RequestInfoContextProvider;
import io.github.pavansharma36.saas.core.common.context.providers.ThreadLocalContextProviders;
import io.github.pavansharma36.saas.core.common.listener.AppLoaderListener;
import io.github.pavansharma36.saas.core.common.utils.CoreUtils;
import io.github.pavansharma36.saas.core.common.utils.ShutdownHooks;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.utils.Constants;
import io.github.pavansharma36.saas.utils.Enums;
import io.github.pavansharma36.saas.utils.Utils;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class ConsumerBootstrapProcessor {

  public static void startConsumer(String appName, Class<?> appConfig,
                                   Queue... queues) {
    try {
      MDCContextProvider.put(Constants.REQUEST_ID_MDC_KEY, Utils.randomRequestId());
      RequestInfoContextProvider.getInstance();
      log.info("Starting consumer for app {} with queues {}", appName, Arrays.asList(queues));
      CoreUtils.initApp(appName, Enums.AppType.WORKER);

      log.info("Creating application context");
      AnnotationConfigApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(appConfig);
      log.info("Created application context {}", applicationContext);

      log.info("Invoking onStart for listeners");
      Collection<AppLoaderListener> listeners =
          applicationContext.getBeansOfType(AppLoaderListener.class).values();
      listeners.forEach(l -> l.onStart(applicationContext));

      registerShutdownHook(applicationContext, listeners);

      Map<String, List<Queue>> queueMap = getTypeQueuesMap(queues);
      log.info("Queue types for process {}", queueMap.keySet());

      log.info("Queue names to process {}", Arrays.stream(queues)
          .map(q -> q.supportedPriorities().stream()
              .map(q::formatQueueName).toList())
          .flatMap(List::stream).toList());

      ConsumerTemplate consumerTemplate = applicationContext.getBean(ConsumerTemplate.class);
      consumerTemplate.consume(applicationContext, queueMap);
    } finally {
      log.info("Clearing all context");
      ThreadLocalContextProviders.clearAll();
    }

  }

  private static void registerShutdownHook(AnnotationConfigApplicationContext context,
                                           Collection<AppLoaderListener> listeners) {
    log.info("Registering app shutdown hook");
    ShutdownHooks.registerShutdownHook(Integer.MAX_VALUE - 10, "StopApplicationContext", () -> {
      log.info("Invoking stop on all AppLoaderListener");
      listeners.forEach(l -> l.onStop(context));
      log.warn("Closing application context");
      context.close();
    });
  }

  private static Map<String, List<Queue>> getTypeQueuesMap(Queue... queues) {
    Map<String, List<Queue>> map = new HashMap<>();
    for (Queue queue : queues) {
      map.computeIfAbsent(queue.type(), dummy -> new LinkedList<>()).add(queue);
    }
    return map;
  }

}
