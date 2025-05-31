package io.github.pavansharma36.saas.core.broker.consumer.api.poller;

import io.github.pavansharma36.saas.core.broker.common.BrokerUtils;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.utils.poll.DelayedLogEmitter;
import io.github.pavansharma36.saas.utils.poll.FixedDelayedLogEmitter;
import io.github.pavansharma36.saas.utils.poll.FixedPollDelayGenerator;
import io.github.pavansharma36.saas.utils.poll.PollDelayGenerator;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PollExecutor<T extends PollResponse> extends Thread {

  private final Queue queue;
  private final PollerConsumer<T> pollerConsumer;
  private final Consumer<byte[]> target;

  @Override
  public void run() {
    PollDelayGenerator delayGenerator = new FixedPollDelayGenerator(TimeUnit.SECONDS.toMillis(1L));
    List<MessagePriority> priorities = MessagePriority.sortDesc(queue.supportedPriorities());

    DelayedLogEmitter logEmitter = new FixedDelayedLogEmitter(Duration.ofSeconds(30L), log);
    while (!Thread.currentThread().isInterrupted()) {

      boolean result = false;

      for (MessagePriority messagePriority : priorities) {
        String queueName = BrokerUtils.queueName(queue, messagePriority);

        boolean blocked = BrokerUtils.isQueueBlocked(queue, messagePriority, logEmitter);
        if (!blocked) {
          log.debug("Polling queue for any new message: {}", queueName);
          try {
            Optional<T> pollRes = pollerConsumer.poll(queueName);
            if (pollRes.isPresent()) {
              try {
                target.accept(pollRes.get().getBody());
              } finally {
                pollerConsumer.ack(pollRes.get());
              }

              result = true;
              break;
            }
          } catch (Exception e) {
            log.error("Error while processing {}", e.getMessage(), e);
          }
        } else {
          logEmitter.info("Not processing any message on queue {} since its blocked", queueName);
        }
      }

      delayGenerator.delay(result);
    }
  }
}
