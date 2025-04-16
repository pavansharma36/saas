package io.github.pavansharma36.core.common.pubsub.publisher;

import io.github.pavansharma36.core.common.pubsub.payload.Payload;

public class NoOpPublisher implements Publisher {

  @Override
  public void publish(String channel, Payload payload) {
    // No-Op
  }
}
