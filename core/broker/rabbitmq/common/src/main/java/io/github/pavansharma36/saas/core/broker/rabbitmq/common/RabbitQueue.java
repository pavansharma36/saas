package io.github.pavansharma36.saas.core.broker.rabbitmq.common;

import io.github.pavansharma36.saas.core.broker.common.api.Queue;

public interface RabbitQueue extends Queue {

  String RABBITMQ = "rabbitmq";

  RabbitExchange exchange();

  String routingKey();

  @Override
  default String type() {
    return RABBITMQ;
  }

}
