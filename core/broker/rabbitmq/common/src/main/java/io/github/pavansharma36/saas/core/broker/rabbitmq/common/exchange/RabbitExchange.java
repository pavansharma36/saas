package io.github.pavansharma36.saas.core.broker.rabbitmq.common.exchange;

import io.github.pavansharma36.saas.utils.Named;

public interface RabbitExchange extends Named {

  RabbitExchange DEFAULT = new RabbitExchange() {
    @Override
    public RabbitExchangeType type() {
      return RabbitExchangeType.DEFAULT;
    }

    @Override
    public String getName() {
      return "";
    }
  };

  RabbitExchangeType type();

}
