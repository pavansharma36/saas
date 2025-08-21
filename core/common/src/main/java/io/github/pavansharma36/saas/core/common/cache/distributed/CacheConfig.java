package io.github.pavansharma36.saas.core.common.cache.distributed;

import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.LoggingCacheErrorHandler;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig implements CachingConfigurer {

  public CacheErrorHandler errorHandler() {
    return new LoggingCacheErrorHandler(false);
  }

}
