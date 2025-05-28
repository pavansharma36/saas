package io.github.pavansharma36.saas.core.broker.consumer.api.listener;

import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ListenExecutor<T extends ListenResponse> extends Thread {

  private final Queue queue;
  private final ListenerConsumer<T> listenConsumer;
  private final Consumer<byte[]> target;

  @Override
  public void run() {
    for (MessagePriority messagePriority : queue.supportedPriorities()) {
      T res = listenConsumer.listen(
          String.format("%s%s", queue.getName(), messagePriority.queueNameSuffix()), target);
    }
  }
}
