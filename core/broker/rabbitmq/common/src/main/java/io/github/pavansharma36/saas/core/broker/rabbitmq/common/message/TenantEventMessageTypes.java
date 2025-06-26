package io.github.pavansharma36.saas.core.broker.rabbitmq.common.message;

import io.github.pavansharma36.saas.core.broker.common.api.MessageType;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.queue.TenantEventQueue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TenantEventMessageTypes implements MessageType {

  TENANT_CREATED(TenantEventQueue.TENANT_CREATED_QUEUE),
  ;

  private final Queue queue;

  @Override
  public Queue queue() {
    return queue;
  }

  @Override
  public String getName() {
    return name();
  }
}
