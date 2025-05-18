package io.github.pavansharma36.saas.core.broker.rabbitmq.common;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RabbitQueueBinding {
  private final RabbitExchange exchange;
  private final String routingKey;
}
