package io.github.pavansharma36.saas.galaxy.common.broker;

import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.CommonRabbitExchange;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.RabbitExchange;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.RabbitQueue;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@RequiredArgsConstructor
public enum GalaxyQueue implements RabbitQueue {

  DEFAULT(GalaxyQueueInfo.DEFAULT.getName(), CommonRabbitExchange.DEFAULT,
      GalaxyQueueInfo.DEFAULT.getName(),
      Arrays.asList(MessagePriority.HIGH, MessagePriority.NORMAL, MessagePriority.LOW),
      Collections.emptyList()),
  ;


  private final String name;

  @Getter
  private final RabbitExchange exchange;

  @Getter
  private final String routingKey;

  @Getter
  private final List<MessagePriority> supportedPriorities;

  @Getter
  private final List<DelayedQueue> supportedDelayedQueues;

  GalaxyQueue(String name, RabbitExchange exchange, String routingKey) {
    this(name, exchange, routingKey, Collections.singletonList(MessagePriority.NORMAL),
        Collections.emptyList());
  }

  @Override
  public String getName() {
    return name;
  }
}
