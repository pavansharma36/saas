package io.github.pavansharma36.saas.core.broker.rabbitmq.common.queue;

import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.exchange.RabbitExchange;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RabbitQueueImpl implements RabbitQueue {

  private final String name;
  private final RabbitExchange exchange;
  private final List<MessagePriority> supportedPriorities;
  private final List<DelayedQueue> supportedDelayedQueues;

  @Override
  public RabbitExchange exchange() {
    return exchange;
  }

  @Override
  public List<MessagePriority> supportedPriorities() {
    return supportedPriorities;
  }
  
  @Override
  public List<DelayedQueue> supportedDelayedQueues() {
    return supportedDelayedQueues;
  }

  @Override
  public String getName() {
    return name;
  }
}
