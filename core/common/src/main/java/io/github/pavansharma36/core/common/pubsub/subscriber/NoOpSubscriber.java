package io.github.pavansharma36.core.common.pubsub.subscriber;

import java.util.function.Consumer;

public class NoOpSubscriber implements Subscriber {
  @Override
  public void subscribe(Consumer<byte[]> consumer, String... channels) {
    // NO-Op
  }
}
