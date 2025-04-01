package io.github.pavansharma36.core.common.config.provider;

import java.util.Map;

public class EnvConfigProvider implements ConfigProvider {
  @Override
  public String getConfig(String key) {
    return System.getenv(key);
  }

  @Override
  public Map<String, String> getAll() {
    return System.getenv();
  }

  @Override
  public int order() {
    // env is last place to look for.
    return Integer.MAX_VALUE;
  }
}
