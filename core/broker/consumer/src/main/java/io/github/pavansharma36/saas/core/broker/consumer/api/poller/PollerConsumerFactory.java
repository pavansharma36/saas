package io.github.pavansharma36.saas.core.broker.consumer.api.poller;

import io.github.pavansharma36.saas.core.broker.common.api.Queue;

public interface PollerConsumerFactory {

  PollerConsumer<?> createPollerConsumer(Queue queue);

}
