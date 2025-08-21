package io.github.pavansharma36.saas.core.common.config.provider;

import java.util.Map;

public interface ConfigProvider {

  String getConfig(String key);

  Map<String, String> getAll();

  int order();

}
