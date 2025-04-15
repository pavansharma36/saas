package io.github.pavansharma36.core.common.pubsub;

import io.github.pavansharma36.core.common.pubsub.payload.Payload;
import io.github.pavansharma36.saas.utils.Enums;

public interface Publisher {
  void publish(Payload payload);

  void publishToApp(Payload payload, String appName);

  void publishToAppType(Payload payload, Enums.AppType appType);

  void publishToAppAndType(Payload payload, String appName, Enums.AppType appType);
}
