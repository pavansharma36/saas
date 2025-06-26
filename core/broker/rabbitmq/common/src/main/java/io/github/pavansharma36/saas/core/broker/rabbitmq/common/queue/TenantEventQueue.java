package io.github.pavansharma36.saas.core.broker.rabbitmq.common.queue;

import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.exchange.RabbitExchange;
import java.util.List;

public class TenantEventQueue extends RabbitQueueImpl {

  public static final Queue TENANT_CREATED_QUEUE = new TenantEventQueue("created");

  public TenantEventQueue(String eventType) {
    super(String.format("tenant.event.%s", eventType), RabbitExchange.DIRECT,
        List.of(MessagePriority.NORMAL), List.of(DelayedQueue.ONE_MINUTE));
  }
}
