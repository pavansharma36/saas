package io.github.pavansharma36.core.common.config;

import static io.github.pavansharma36.core.common.config.provider.ConfigProviders.getConfig;

import io.github.pavansharma36.saas.utils.ex.AppConfigurationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Config {

  private static String getStringOrThrow(String key) {
    String value = getConfig(key);
    if (value == null) {
      throw new AppConfigurationException("Config not found : " + key);
    }
    return value;
  }

  public static String get(String key) {
    return getStringOrThrow(key);
  }

  public static String get(String key, String def) {
    String value = getConfig(key);
    return value == null ? def : value;
  }

  public static int getInt(String key) {
    return Integer.parseInt(getStringOrThrow(key));
  }

  public static int getInt(String key, int def) {
    String value = getConfig(key);
    return value == null ? def : Integer.parseInt(value);
  }

  public static long getLong(String key) {
    return Long.parseLong(getStringOrThrow(key));
  }

  public static long getLong(String key, long def) {
    String value = getConfig(key);
    return value == null ? def : Long.parseLong(value);
  }

}
