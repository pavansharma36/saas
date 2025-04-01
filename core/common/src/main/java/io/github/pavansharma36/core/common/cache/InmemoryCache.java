package io.github.pavansharma36.core.common.cache;

public interface InmemoryCache {

  String cacheName();

  void clearCache(String cacheKey);

  void cleanCache();

  int cacheValiditySeconds();

}
