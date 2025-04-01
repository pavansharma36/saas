package io.github.pavansharma36.core.common.config.provider;

import io.github.pavansharma36.saas.utils.resource.ResourceUtils;
import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertiesFileConfigProvider implements ConfigProvider {

  private final Properties properties;
  private final int order;

  public PropertiesFileConfigProvider(String path, int order) {
    File file = ResourceUtils.findFileOrThrow(path, 5);
    this.properties = ResourceUtils.properties(file);
    this.order = order;
  }

  @Override
  public String getConfig(String key) {
    return properties.getProperty(key);
  }

  @Override
  public Map<String, String> getAll() {
    return properties.entrySet().stream()
        .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
  }

  @Override
  public int order() {
    return order;
  }
}
