package io.github.pavansharma36.core.common.pubsub.publisher;

import io.github.pavansharma36.core.common.pubsub.payload.Payload;

public interface Publisher {

  void publish(String channel, Payload payload);

}
