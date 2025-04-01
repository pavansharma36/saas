package io.github.pavansharma36.core.common.config;

import static io.github.pavansharma36.core.common.config.provider.ConfigProviders.getAll;
import static io.github.pavansharma36.core.common.config.provider.ConfigProviders.getConfig;

import io.github.pavansharma36.saas.utils.ex.AppConfigurationException;
import java.util.Map;
import java.util.stream.Collectors;
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

  public static boolean getBoolean(String key) {
    return Boolean.parseBoolean(getStringOrThrow(key));
  }

  public static boolean getBoolean(String key, boolean def) {
    String value = getConfig(key);
    return value == null ? def : Boolean.parseBoolean(value);
  }

  public static Map<String, String> getAllByPrefix(String prefix) {
    return getAll().entrySet().stream()
        .filter(e -> e.getKey().startsWith(prefix))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

}
