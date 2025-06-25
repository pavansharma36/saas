package io.github.pavansharma36.saas.core.broker.rabbitmq.common.queue;

import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.exchange.RabbitExchange;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.exchange.RabbitExchangeType;
import java.util.List;

public class TenantEventQueue extends RabbitQueueImpl {

  public static final RabbitExchange TENANT_EVENT_EXCHANGE = new RabbitExchange() {
    @Override
    public RabbitExchangeType type() {
      return RabbitExchangeType.TOPIC;
    }

    @Override
    public String getName() {
      return "tenant.event";
    }
  };

  public TenantEventQueue(String eventType) {
    super(String.format("%s.%s", TENANT_EVENT_EXCHANGE.getName(), eventType), TENANT_EVENT_EXCHANGE,
        List.of(MessagePriority.NORMAL), List.of(DelayedQueue.ONE_MINUTE));
  }
}
