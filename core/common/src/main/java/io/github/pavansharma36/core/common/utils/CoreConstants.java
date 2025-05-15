package io.github.pavansharma36.core.common.utils;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.utils.Enums;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class CoreConstants {

  public static final String PROCESS_UUID = UUID.randomUUID().toString();
  public static final String APP_NAME = Config.get("app.name");
  public static final Enums.AppType APP_TYPE = Enums.AppType.fromName(Config.get("app.type"));

  public static final String PUBSUB_IMPL_CONF = "pubsub.impl";
  public static final String MUTEX_IMPL_CONF = "mutex.impl";

}
