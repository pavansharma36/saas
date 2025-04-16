package io.github.pavansharma36.core.common.pubsub.subscriber.handler;

import java.io.Serializable;

public interface SubscriptionHandler {
  String eventType();

  void handle(Serializable data);
}
