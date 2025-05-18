package io.github.pavansharma36.saas.core.broker.rabbitmq.common;

import io.github.pavansharma36.saas.utils.Named;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RabbitExchangeType implements Named {

  DEFAULT("default"),
  DIRECT("direct"),
  TOPIC("topic"),
  ;

  private final String name;

  @Override
  public String getName() {
    return name;
  }
}
