package io.github.pavansharma36.core.common.config.provider;

import io.github.pavansharma36.saas.utils.Enums;
import io.github.pavansharma36.saas.utils.resource.ResourceUtils;
import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertiesFileConfigProvider implements ConfigProvider {

  private final String filePath;
  private final Properties properties;
  private final int order;

  public PropertiesFileConfigProvider(String path, int order) {
    File file = ResourceUtils.findFileOrThrow(path, 5);
    this.filePath = file.getAbsolutePath();
    this.properties = ResourceUtils.properties(file);
    this.order = order;
  }

  public static void registerPropertiesFileProviders(String appName, Enums.AppType appType) {
    System.setProperty("app.type", appType.getName());
    ConfigProviders.registerConfigProvider(
        new PropertiesFileConfigProvider(
            String.format("conf/app_type/%s.properties", appType.name().toLowerCase()), 20000));
    ConfigProviders.registerConfigProvider(
        new PropertiesFileConfigProvider(String.format("conf/app_name/%s.properties", appName),
            10000));
    ConfigProviders.registerConfigProvider(
        new PropertiesFileConfigProvider("conf/common.properties", Integer.MAX_VALUE - 20));
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

  @Override
  public String toString() {
    return String.format("PropertiesFileConfigProvider(%s)", filePath);
  }
}
