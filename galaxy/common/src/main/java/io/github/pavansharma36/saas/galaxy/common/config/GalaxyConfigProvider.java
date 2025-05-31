package io.github.pavansharma36.saas.galaxy.common.config;

import io.github.pavansharma36.core.common.cache.InmemoryCache;
import io.github.pavansharma36.core.common.cache.InmemoryCaches;
import io.github.pavansharma36.core.common.config.provider.ConfigProvider;
import io.github.pavansharma36.core.common.config.provider.ConfigProviders;
import io.github.pavansharma36.core.common.utils.CoreConstants;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model.Config;
import io.github.pavansharma36.saas.galaxy.common.service.ConfigService;
import io.github.pavansharma36.saas.utils.collections.CollectionUtils;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GalaxyConfigProvider implements ConfigProvider, InmemoryCache {

  private final String appName;
  private final String appType;
  private final ConfigService configService;
  private final Map<String, String> confs = new ConcurrentHashMap<>();

  public GalaxyConfigProvider(ConfigService configService) {
    this.configService = configService;
    this.appName = CoreConstants.APP_NAME;
    this.appType = CoreConstants.APP_TYPE.getName();
    cacheConfs();
    ConfigProviders.registerConfigProvider(this);
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
    return 100;
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
    return io.github.pavansharma36.core.common.config.Config.getInt("galaxy.config.cache.interval",
        1800);
  }

  private void cacheConfs() {
    Collection<Config>
        res = configService.getConfigValues(appName, appType);
    Map<String, String> c = CollectionUtils.nullSafe(res).stream()
        .collect(Collectors.toMap(Config::getConfigName, Config::getConfigValue));
    Set<String> existingKey = this.confs.keySet();
    existingKey.removeAll(c.keySet());
    if (!existingKey.isEmpty()) {
      log.info("{} keys are not present removing from cache", existingKey);
      existingKey.forEach(this.confs::remove);
    }
    this.confs.putAll(c);
  }
}
