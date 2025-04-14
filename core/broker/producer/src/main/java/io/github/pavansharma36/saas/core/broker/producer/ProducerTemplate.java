package io.github.pavansharma36.saas.core.broker.producer;

import io.github.pavansharma36.saas.core.broker.common.Message;

public interface ProducerTemplate {
  void produce(Message message);
}
