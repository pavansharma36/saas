package io.github.pavansharma36.saas.core.broker.rabbitmq.common;

import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;

public interface RabbitQueue extends Queue {

  String RABBITMQ = "rabbitmq";

  RabbitExchange exchange();

  String routingKey(MessagePriority priority);

  String routingKey(DelayedQueue delayedQueue);

  @Override
  default String type() {
    return RABBITMQ;
  }

}
