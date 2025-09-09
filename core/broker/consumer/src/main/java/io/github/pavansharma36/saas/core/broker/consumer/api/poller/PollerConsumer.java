package io.github.pavansharma36.saas.core.broker.consumer.api.poller;

import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.consumer.api.ConsumerLifeCycle;
import java.util.Optional;

public interface PollerConsumer<T extends PollResponse> extends ConsumerLifeCycle {

  Optional<T> poll(Queue queue);

  void ack(T response);

}
