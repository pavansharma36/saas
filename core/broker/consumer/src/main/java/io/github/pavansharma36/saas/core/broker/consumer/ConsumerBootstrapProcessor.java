package io.github.pavansharma36.saas.core.broker.consumer;

import io.github.pavansharma36.core.common.listener.AppLoaderListener;
import io.github.pavansharma36.core.common.utils.CoreUtils;
import io.github.pavansharma36.saas.core.broker.common.Queue;
import io.github.pavansharma36.saas.utils.Enums;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class ConsumerBootstrapProcessor {

  public static void startConsumer(String appName, Class<?> appConfig,
                                   Queue... queues) {
    log.info("Starting consumer for app {} with queues {}", appName, Arrays.asList(queues));
    CoreUtils.initApp(appName, Enums.AppType.WORKER);

    log.info("Creating application context");
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(appConfig);
    log.info("Created application context {}", applicationContext);

    log.info("Invoking onStart for listeners");
    applicationContext.getBeansOfType(AppLoaderListener.class).values()
        .forEach(l -> l.onStart(applicationContext));

    Map<String, List<Queue>> queueMap = getTypeQueuesMap(queues);
    log.info("Queue types for process {}", queueMap.keySet());

    log.info("Queue names to process {}", Arrays.stream(queues)
        .map(q -> q.supportedPriorities().stream().map(q::formatQueueName).toList())
        .flatMap(List::stream).toList());

    Map<String, ConsumerTemplate> consumers = getTypeConsumerMap(applicationContext);
    log.info("Consumer types that are defined are: {}", consumers.keySet());

    Set<String> queueTypes = new HashSet<>(queueMap.keySet());
    queueTypes.removeAll(consumers.keySet());

    if (!queueTypes.isEmpty()) {
      throw new ServerRuntimeException(
          String.format("Queue type implementations not found %s", queueTypes));
    }

  }

  private static Map<String, List<Queue>> getTypeQueuesMap(Queue... queues) {
    Map<String, List<Queue>> map = new HashMap<>();
    for (Queue queue : queues) {
      map.computeIfAbsent(queue.type(), dummy -> new LinkedList<>()).add(queue);
    }
    return map;
  }

  private static Map<String, ConsumerTemplate> getTypeConsumerMap(ApplicationContext context) {
    Map<String, ConsumerTemplate> consumerTemplates =
        context.getBeansOfType(ConsumerTemplate.class);
    return consumerTemplates.values().stream()
        .collect(Collectors.toMap(ConsumerTemplate::type, k -> k));
  }

}
