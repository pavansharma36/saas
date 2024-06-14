package io.github.pavansharma36.core.common.config.provider;

public class SystemConfigProvider implements ConfigProvider {
  @Override
  public String getConfig(String key) {
    return System.getProperty(key);
  }

  @Override
  public int order() {
    // system if before env.
    return Integer.MAX_VALUE - 10;
  }
}
