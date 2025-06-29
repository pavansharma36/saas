package io.github.pavansharma36.saas.galaxy.common.broker;

import io.github.pavansharma36.saas.core.broker.common.api.MessageType;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.galaxy.common.utils.GalaxyConstants;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GalaxyMessageType implements MessageType {

  TEST(GalaxyConstants.GALAXY_QUEUE),
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
