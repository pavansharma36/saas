package io.github.pavansharma36.core.common.config.provider;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ConfigProviders {

  private static final List<ConfigProvider> providers = new LinkedList<>();

  static {
    registerConfigProvider(new SystemConfigProvider());
    registerConfigProvider(new EnvConfigProvider());
    registerConfigProvider(
        new PropertiesFileConfigProvider("conf/common.properties", Integer.MAX_VALUE - 20));
  }

  public static synchronized void registerConfigProvider(ConfigProvider provider) {
    providers.add(provider);
    providers.sort(Comparator.comparing(ConfigProvider::order));
  }

  public static String getConfig(String key) {
    for (ConfigProvider provider : providers) {
      String a = provider.getConfig(key);
      if (a != null) {
        return a;
      }
    }
    return null;
  }

  public static Map<String, String> getAll() {
    Map<String, String> m = new HashMap<>();
    for (ConfigProvider provider : providers.reversed()) {
      m.putAll(provider.getAll());
    }
    return m;
  }

}
