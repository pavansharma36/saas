package io.github.pavansharma36.saas.galaxy.common.broker;

import io.github.pavansharma36.saas.core.broker.common.BrokerUtils;
import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.CommonRabbitExchange;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.RabbitExchange;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.RabbitQueue;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@RequiredArgsConstructor
public enum GalaxyQueue implements RabbitQueue {

  GALAXY("galaxy", CommonRabbitExchange.DEFAULT,
      List.of(MessagePriority.NORMAL),
      List.of(DelayedQueue.ONE_MINUTE)),
  ;


  private final String name;

  @Getter
  private final RabbitExchange exchange;

  @Getter
  private final List<MessagePriority> supportedPriorities;

  @Getter
  private final List<DelayedQueue> supportedDelayedQueues;

  @Override
  public String routingKey(MessagePriority priority) {
    return BrokerUtils.queueName(this, priority);
  }

  @Override
  public String routingKey(DelayedQueue delayedQueue) {
    return BrokerUtils.queueName(this, delayedQueue);
  }

  @Override
  public String getName() {
    return name;
  }
}
