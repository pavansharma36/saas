package io.github.pavansharma36.saas.core.broker.producer;

import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;

public interface ProducerTemplate {
  void produce(Queue queue, MessagePriority priority, byte[] data);

  void produce(Queue queue, DelayedQueue delayedQueue, byte[] data);

  String type();
}
