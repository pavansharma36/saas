package io.github.pavansharma36.saas.core.broker.consumer.api.poller;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.utils.poll.FixedPollDelayGenerator;
import io.github.pavansharma36.saas.utils.poll.PollDelayGenerator;
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
    while (!Thread.currentThread().isInterrupted()) {

      boolean result = false;

      boolean block = Config.getBoolean("queue.block.all", false);

      if (block) {
        log.info("All queues are blocked, not polling for any message");
      } else {
        for (MessagePriority messagePriority : priorities) {
          String queueName =
              String.format("%s%s", queue.getName(), messagePriority.queueNameSuffix());

          block = Config.getBoolean(String.format("%s.queue.block.all", queueName), false);
          if (block) {
            log.info("Queues blocked, not polling for any message : {}", queueName);
          } else {
            log.debug("Polling queue for any new message: {}", queueName);
            Optional<T> pollRes = pollerConsumer.poll(queueName);
            if (pollRes.isPresent()) {
              target.accept(pollRes.get().getBody());

              result = true;
              break;
            }
          }
        }
      }

      delayGenerator.delay(result);
    }
  }
}
