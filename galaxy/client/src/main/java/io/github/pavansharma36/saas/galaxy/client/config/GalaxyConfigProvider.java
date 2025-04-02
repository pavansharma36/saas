package io.github.pavansharma36.saas.galaxy.client.config;

import io.github.pavansharma36.core.common.cache.InmemoryCache;
import io.github.pavansharma36.core.common.cache.InmemoryCaches;
import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.core.common.config.provider.ConfigProvider;
import io.github.pavansharma36.saas.core.dto.ListResponseObject;
import io.github.pavansharma36.saas.galaxy.api.ConfigApi;
import io.github.pavansharma36.saas.galaxy.client.GalaxyClientFactory;
import io.github.pavansharma36.saas.galaxy.dto.config.ConfigValueDto;
import io.github.pavansharma36.saas.utils.Enums;
import io.github.pavansharma36.saas.utils.collections.CollectionUtils;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GalaxyConfigProvider implements ConfigProvider, InmemoryCache {

  private final ConfigApi configApi = GalaxyClientFactory.configApi();
  private final Map<String, String> confs = new ConcurrentHashMap<>();
  private final String appName;
  private final Enums.AppType appType;

  public GalaxyConfigProvider(String appName, Enums.AppType appType) {
    this.appName = appName;
    this.appType = appType;
    cacheConfs();
    InmemoryCaches.register(this);
  }

  @Override
  public String getConfig(String key) {
    return confs.get(key);
  }

  @Override
  public Map<String, String> getAll() {
    return confs;
  }

  @Override
  public int order() {
    return 1000;
  }

  @Override
  public String cacheName() {
    return "galaxy-config";
  }

  @Override
  public void clearCache(String cacheKey) {
    cacheConfs();
  }

  @Override
  public void cleanCache() {
    cacheConfs();
  }

  @Override
  public int cacheValiditySeconds() {
    return Config.getInt("galaxy.config.cache.interval", 1800);
  }

  private void cacheConfs() {
    ListResponseObject<ConfigValueDto>
        res = configApi.getAll(appName, appType.name());
    if (res.isSuccess()) {
      Map<String, String> c = CollectionUtils.nullSafeList(res.getData()).stream()
          .collect(Collectors.toMap(ConfigValueDto::getKey, ConfigValueDto::getValue));
      Set<String> existingKey = this.confs.keySet();
      existingKey.removeAll(c.keySet());
      if (!existingKey.isEmpty()) {
        log.info("{} keys are not present removing from cache", existingKey);
        existingKey.forEach(this.confs::remove);
      }
      this.confs.putAll(c);
    } else {
      log.warn("Non success response from config api {}", res);
    }
  }

  @Override
  public String toString() {
    return String.format("GalaxyConfigProvider(appName=%s, appType=%s)", appName, appType);
  }
}
