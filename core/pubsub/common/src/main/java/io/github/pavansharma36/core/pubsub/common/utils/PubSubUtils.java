package io.github.pavansharma36.core.pubsub.common.utils;

import io.github.pavansharma36.core.common.utils.CoreUtils;
import io.github.pavansharma36.saas.utils.Enums;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class PubSubUtils {

  private static final String SEPARATOR = "-";

  public static String getNamespace() {
    return CoreUtils.getEnv() + SEPARATOR + "channel";
  }

  public static String getChannelName(String namespace, String appName) {
    return namespace + SEPARATOR + appName;
  }

  public static String getChannelName(String namespace, Enums.AppType appType) {
    return namespace + SEPARATOR + appType.toLowerCase();
  }

  public static String getChannelName(String namespace, String appName, Enums.AppType appType) {
    return namespace + SEPARATOR + appName + SEPARATOR + appType.toLowerCase();
  }

}
