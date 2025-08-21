package io.github.pavansharma36.saas.core.common.config.provider;

import io.github.pavansharma36.saas.utils.resource.ResourceUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ClasspathPropertiesConfigProvider implements ConfigProvider {

  private final Map<String, String> configs;

  public ClasspathPropertiesConfigProvider(String path, Class<?> clazz) {
    Properties props = ResourceUtils.classpathProperties(path, clazz);
    configs = new HashMap<>(props.size());
    props.forEach((k, v) -> configs.put((String) k, (String) v));
  }

  @Override
  public String getConfig(String key) {
    return configs.get(key);
  }

  @Override
  public Map<String, String> getAll() {
    return configs;
  }

  @Override
  public int order() {
    return Integer.MAX_VALUE;
  }
}
