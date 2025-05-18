package io.github.pavansharma36.saas.galaxy.common.broker;

import io.github.pavansharma36.saas.core.broker.rabbitmq.common.RabbitQueueBinding;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.RabbitQueueInfo;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GalaxyQueueInfo implements RabbitQueueInfo {

  DEFAULT("galaxy", Collections.emptyList()),
  ;

  private final String name;
  private final List<RabbitQueueBinding> bindings;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public List<RabbitQueueBinding> bindings() {
    return bindings;
  }
}
