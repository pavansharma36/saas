package io.github.pavansharma36.saas.core.broker.rabbitmq.common;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CommonRabbitExchange implements RabbitExchange {

  DEFAULT("", RabbitExchangeType.DEFAULT),
  ;

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
