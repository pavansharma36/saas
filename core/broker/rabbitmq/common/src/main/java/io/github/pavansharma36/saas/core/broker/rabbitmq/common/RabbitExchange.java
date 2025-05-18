package io.github.pavansharma36.saas.core.broker.rabbitmq.common;

import io.github.pavansharma36.saas.utils.Named;

public interface RabbitExchange extends Named {

  RabbitExchangeType type();

}
