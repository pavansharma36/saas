package io.github.pavansharma36.saas.core.common.cache.inmemory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractInMemoryCache<T> implements InmemoryCache {

  protected Map<String, T> CACHE = new ConcurrentHashMap<>();

  @Override
  public void clearCache(String cacheKey) {
    CACHE.remove(cacheKey);
  }

  @Override
  public void cleanCache() {
    CACHE.clear();
  }

}
