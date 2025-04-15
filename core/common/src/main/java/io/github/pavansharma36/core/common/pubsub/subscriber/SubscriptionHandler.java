package io.github.pavansharma36.core.common.pubsub.subscriber;

import java.io.Serializable;

public interface SubscriptionHandler {
  String eventType();

  void handle(Serializable data);
}
