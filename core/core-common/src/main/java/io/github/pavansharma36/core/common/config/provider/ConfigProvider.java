package io.github.pavansharma36.core.common.config.provider;

public interface ConfigProvider {

  String getConfig(String key);

  int order();

}
