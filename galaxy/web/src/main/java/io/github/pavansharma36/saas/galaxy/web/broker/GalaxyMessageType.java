package io.github.pavansharma36.saas.galaxy.web.broker;

import io.github.pavansharma36.saas.core.broker.common.MessageType;
import io.github.pavansharma36.saas.core.broker.common.QueueType;

public enum GalaxyMessageType implements MessageType {
  ;

  @Override
  public QueueType queue() {
    return null;
  }

  @Override
  public String getName() {
    return "";
  }
}
