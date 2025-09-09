package io.github.pavansharma36.saas.core.broker.consumer.api.listener;

import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.consumer.api.ConsumerLifeCycle;
import java.util.function.Consumer;

public interface ListenerConsumer<T extends ListenResponse> extends ConsumerLifeCycle {
  T listen(Queue queue, Consumer<byte[]> consumer);

  void stop(T listener);
}
