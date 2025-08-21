package io.github.pavansharma36.saas.core.common.pubsub.subscriber;

import java.util.function.Consumer;

public interface Subscriber {
  void subscribe(Consumer<byte[]> consumer, String... channels);

}
