package io.github.pavansharma36.core.common.config.provider;

public class EnvConfigProvider implements ConfigProvider {
  @Override
  public String getConfig(String key) {
    return System.getenv(key);
  }

  @Override
  public int order() {
    // env is last place to look for.
    return Integer.MAX_VALUE;
  }
}
