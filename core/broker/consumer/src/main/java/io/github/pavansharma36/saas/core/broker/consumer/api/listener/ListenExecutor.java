package io.github.pavansharma36.saas.core.broker.consumer.api.listener;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.core.broker.common.BrokerUtils;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.utils.poll.DelayedLogEmitter;
import io.github.pavansharma36.saas.utils.poll.FixedDelayedLogEmitter;
import io.github.pavansharma36.saas.utils.poll.FixedPollDelayGenerator;
import io.github.pavansharma36.saas.utils.poll.PollDelayGenerator;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListenExecutor<T extends ListenResponse> extends Thread {

  private final Queue queue;
  private final ListenerConsumer<T> listenConsumer;
  private final Consumer<byte[]> target;

  private final Map<String, T> runningQueues;

  public ListenExecutor(Queue queue, ListenerConsumer<T> listenConsumer, Consumer<byte[]> target) {
    this.queue = queue;
    this.listenConsumer = listenConsumer;
    this.target = target;

    this.runningQueues = new HashMap<>(queue.supportedPriorities().size());
  }


  @Override
  public void run() {
    final PollDelayGenerator blockCheckDelayGenerator = new FixedPollDelayGenerator(
        Duration.ofSeconds(Config.getInt("listen.blocked.check.delay.seconds", 10)).toMillis());
    final DelayedLogEmitter logEmitter = new FixedDelayedLogEmitter(Duration.ofSeconds(30), log);
    while (!Thread.currentThread().isInterrupted()) {
      for (MessagePriority messagePriority : queue.supportedPriorities()) {
        String queueName = BrokerUtils.queueName(queue, messagePriority);
        boolean blocked = BrokerUtils.isQueueBlocked(queue, messagePriority, logEmitter);
        if (blocked) {
          if (runningQueues.containsKey(queueName)) {
            log.info("Stopping execution of running queue {}: {}", queueName,
                runningQueues.get(queueName));
            listenConsumer.stop(runningQueues.get(queueName));
            runningQueues.remove(queueName);
          }
        } else if (!runningQueues.containsKey(queueName)) {
          log.info("Starting listener for queue {}", queueName);
          T res = listenConsumer.listen(
              String.format("%s%s", queue.getName(), messagePriority.queueNameSuffix()), target);
          runningQueues.put(queueName, res);
        }
      }

      blockCheckDelayGenerator.delay();
    }

  }
}
