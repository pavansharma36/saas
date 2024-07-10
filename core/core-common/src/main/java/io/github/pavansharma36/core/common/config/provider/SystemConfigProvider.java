package io.github.pavansharma36.core.common.config.provider;

import java.util.Map;
import java.util.stream.Collectors;

public class SystemConfigProvider implements ConfigProvider {
  @Override
  public String getConfig(String key) {
    return System.getProperty(key);
  }

  @Override
  public Map<String, String> getAll() {
    return System.getProperties().entrySet().stream()
        .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
  }

  @Override
  public int order() {
    // system if before env.
    return Integer.MAX_VALUE - 10;
  }
}
