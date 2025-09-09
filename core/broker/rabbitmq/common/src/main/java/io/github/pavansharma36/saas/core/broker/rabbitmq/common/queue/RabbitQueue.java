package io.github.pavansharma36.saas.core.broker.rabbitmq.common.queue;

import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.exchange.RabbitExchange;

public interface RabbitQueue extends Queue {

  String RABBITMQ = "rabbitmq";

  RabbitExchange exchange();

  default String routingKey() {
    return getName();
  }

  default String routingKey(DelayedQueue delayedQueue) {
    return formatQueueName(delayedQueue);
  }

  @Override
  default String type() {
    return RABBITMQ;
  }

  @Override
  default String formatSeparator() {
    return ".";
  }
}
