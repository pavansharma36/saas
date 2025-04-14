package io.github.pavansharma36.saas.core.broker.consumer;


import io.github.pavansharma36.saas.core.broker.common.QueueType;

public interface ConsumerTemplate {

  void consume(QueueType... queueType);

}
