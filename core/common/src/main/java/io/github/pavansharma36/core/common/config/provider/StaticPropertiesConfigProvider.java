package io.github.pavansharma36.core.common.config.provider;

import io.github.pavansharma36.saas.utils.resource.ResourceUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class StaticPropertiesConfigProvider implements ConfigProvider {

  private static final Map<String, String> CONFIGS;

  static {
    Properties props = ResourceUtils.classpathProperties("static.properties",
        StaticPropertiesConfigProvider.class);
    CONFIGS = new HashMap<>(props.size());
    props.forEach((k, v) -> CONFIGS.put((String) k, (String) v));
  }

  @Override
  public String getConfig(String key) {
    return CONFIGS.get(key);
  }

  @Override
  public Map<String, String> getAll() {
    return CONFIGS;
  }

  @Override
  public int order() {
    return Integer.MAX_VALUE;
  }
}
