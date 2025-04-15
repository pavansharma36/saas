package io.github.pavansharma36.core.common.pubsub.subscriber;

import io.github.pavansharma36.saas.utils.Enums;
import java.util.function.Consumer;

//@Component
public class NoOpSubscriber implements Subscriber {
  @Override
  public void subscribe(Consumer<byte[]> consumer) {

  }

  @Override
  public void subscribe(String appName, Consumer<byte[]> consumer) {

  }

  @Override
  public void subscribe(Enums.AppType appType, Consumer<byte[]> consumer) {

  }

  @Override
  public void subscribe(String appName, Enums.AppType appType, Consumer<byte[]> consumer) {

  }
}
