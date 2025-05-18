package io.github.pavansharma36.saas.core.broker.rabbitmq.common;

import io.github.pavansharma36.saas.utils.Named;
import java.util.List;


public interface RabbitQueueInfo extends Named {
  List<RabbitQueueBinding> bindings();
}
