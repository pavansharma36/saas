package io.github.pavansharma36.saas.core.broker.rabbitmq.common.exchange;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RabbitExchangeImpl implements RabbitExchange {

  private final String name;
  private final RabbitExchangeType type;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public RabbitExchangeType type() {
    return type;
  }
}
