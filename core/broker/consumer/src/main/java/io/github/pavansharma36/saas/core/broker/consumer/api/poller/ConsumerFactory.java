package io.github.pavansharma36.saas.core.broker.consumer.api.poller;

import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenResponse;
import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenerConsumer;

public interface ConsumerFactory<L extends ListenResponse, P extends PollResponse> {

  ListenerConsumer<L> createListenerConsumer(Queue queue);

  PollerConsumer<P> createPollerConsumer(Queue queue);

  String type();

}
