package io.github.pavansharma36.saas.core.dao.redis.cache;

import io.github.pavansharma36.saas.core.common.config.Config;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
@RequiredArgsConstructor
@Conditional(RedisCacheCondition.class)
public class RedisCacheConfig {

  private final RedisConnectionFactory redisConnectionFactory;

  @Bean
  @Lazy
  public CacheManager cacheManager() {
    long defaultExpiryMinutes = Config.getLong("redis.cache.default.ttl.minutes",
        Config.getLong("cache.default.ttl.minutes", 60L));
    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofMinutes(defaultExpiryMinutes)) // Set default TTL for cache entries
        .disableCachingNullValues(); // Prevent caching of null values
    return RedisCacheManager.builder(redisConnectionFactory)
        .cacheDefaults(config)
        .build();
  }

}
