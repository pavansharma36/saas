package io.github.pavansharma36.core.common.utils;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.utils.Enums;
import io.github.pavansharma36.saas.utils.Utils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class CoreConstants {

  public static final String PROCESS_UUID = Utils.randomRequestId();
  public static final String APP_NAME = Config.get("app.name");
  public static final Enums.AppType APP_TYPE = Enums.AppType.fromName(Config.get("app.type"));

  public static final String PUBSUB_IMPL_CONF = "pubsub.impl";
  public static final String MUTEX_IMPL_CONF = "mutex.impl";
  public static final String CACHE_IMPL_CONF = "cache.impl";

  public static final String SYSTEM_USER_ID = "system";

}
