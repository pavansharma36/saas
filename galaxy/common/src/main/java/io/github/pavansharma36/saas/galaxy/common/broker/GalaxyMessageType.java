package io.github.pavansharma36.saas.galaxy.common.broker;

import io.github.pavansharma36.saas.core.broker.common.api.MessageType;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GalaxyMessageType implements MessageType {

  TEST(GalaxyQueue.GALAXY),
  ;

  private final Queue queue;

  @Override
  public Queue queue() {
    return null;
  }

  @Override
  public String getName() {
    return name();
  }
}
