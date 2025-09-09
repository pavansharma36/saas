package io.github.pavansharma36.saas.core.broker.producer;

import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageSerializablePayload;
import java.util.function.Function;

public interface ProducerTemplate {
  void produce(Queue queue, MessageSerializablePayload payload,
               Function<MessageSerializablePayload, byte[]> serializer);

  void produce(Queue queue, DelayedQueue delayedQueue,
               MessageSerializablePayload payload,
               Function<MessageSerializablePayload, byte[]> serializer);

  String type();
}
