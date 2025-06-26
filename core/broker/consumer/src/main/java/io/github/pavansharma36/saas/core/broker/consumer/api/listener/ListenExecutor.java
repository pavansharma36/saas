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
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListenExecutor<T extends ListenResponse> extends Thread {

  private final Queue queue;
  private final MessagePriority messagePriority;
  private final ListenerConsumer<T> listenConsumer;
  private final Consumer<byte[]> target;

  public ListenExecutor(Queue queue, MessagePriority messagePriority,
                        ListenerConsumer<T> listenConsumer,
                        Consumer<byte[]> target) {
    this.queue = queue;
    this.messagePriority = messagePriority;
    this.listenConsumer = listenConsumer;
    this.target = target;
  }


  @Override
  public void run() {
    final PollDelayGenerator blockCheckDelayGenerator = new FixedPollDelayGenerator(
        Duration.ofSeconds(Config.getInt("listen.blocked.check.delay.seconds", 10)).toMillis());
    final DelayedLogEmitter logEmitter = new FixedDelayedLogEmitter(Duration.ofSeconds(30), log);
    T listener = null;
    while (!Thread.currentThread().isInterrupted()) {
      String queueName = queue.formatQueueName(messagePriority);
      boolean blocked = BrokerUtils.isQueueBlocked(queue, messagePriority, logEmitter);
      try {
        if (blocked) {
          if (listener != null) {
            log.info("Stopping execution of running queue {}: {}", queueName,
                listener);
            listenConsumer.stop(listener);
            listener = null;
          } else {
            logEmitter.info("Not processing any message on queue {} since its blocked", queueName);
          }
        } else if (listener == null) {
          log.info("Starting listener for queue {}", queueName);
          listener = listenConsumer.listen(queueName, target);
        }
      } catch (Exception e) {
        log.error("Error in ListenExecutor run {}", e.getMessage(), e);
      }

      blockCheckDelayGenerator.delay();
    }

  }
}
