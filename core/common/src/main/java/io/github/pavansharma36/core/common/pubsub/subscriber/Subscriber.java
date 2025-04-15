package io.github.pavansharma36.core.common.pubsub.subscriber;

import io.github.pavansharma36.saas.utils.Enums;
import java.util.function.Consumer;

public interface Subscriber {
  void subscribe(Consumer<byte[]> consumer);

  void subscribe(String appName, Consumer<byte[]> consumer);

  void subscribe(Enums.AppType appType, Consumer<byte[]> consumer);

  void subscribe(String appName, Enums.AppType appType, Consumer<byte[]> consumer);
}
