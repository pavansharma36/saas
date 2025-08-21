package io.github.pavansharma36.saas.core.common.pubsub.publisher;

import io.github.pavansharma36.saas.core.common.pubsub.payload.Payload;

public interface Publisher {

  void publish(String channel, Payload payload);

}
