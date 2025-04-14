package io.github.pavansharma36.core.pubsub.common;

import io.github.pavansharma36.core.pubsub.common.payload.Payload;
import io.github.pavansharma36.saas.utils.Enums;
import java.util.function.Consumer;

public interface Subscriber {
  void subscribe(Consumer<Payload> consumer);

  void subscribe(String appName, Consumer<Payload> consumer);

  void subscribe(Enums.AppType appType, Consumer<Payload> consumer);

  void subscribe(String appName, Enums.AppType appType, Consumer<Payload> consumer);
}
