package io.github.pavansharma36.saas.galaxy.web.broker;

import io.github.pavansharma36.saas.core.broker.common.MessageType;
import io.github.pavansharma36.saas.core.broker.common.Queue;
import io.github.pavansharma36.saas.galaxy.common.broker.GalaxyQueue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GalaxyMessageType implements MessageType {

  TENANT_CREATED("galaxy-tenant-create", GalaxyQueue.DEFAULT);

  private final String name;
  private final GalaxyQueue queue;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Queue queue() {
    return queue;
  }
}
