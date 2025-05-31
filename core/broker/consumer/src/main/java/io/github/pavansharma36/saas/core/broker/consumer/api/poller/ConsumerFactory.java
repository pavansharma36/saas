package io.github.pavansharma36.saas.core.broker.consumer.api.poller;

import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenResponse;
import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenerConsumer;

public interface ConsumerFactory<L extends ListenResponse, P extends PollResponse> {

  ListenerConsumer<L> createListenerConsumer();

  PollerConsumer<P> createPollerConsumer();

  String type();

}
