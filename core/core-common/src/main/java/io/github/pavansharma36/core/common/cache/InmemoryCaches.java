package io.github.pavansharma36.core.common.cache;

import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class InmemoryCaches {

  private static final Map<String, InmemoryCache> CACHES = new HashMap<>();
  private static final Map<String, Long> CACHE_CLEAN_TIMESTAMP = new HashMap<>();

  public static void register(InmemoryCache cache) {
    if (CACHES.putIfAbsent(cache.cacheName(), cache) != null) {
      throw new ServerRuntimeException(
          String.format("Cache with name %s already registered", cache.cacheName()));
    }
    log.info("Registered cache {}", cache.cacheName());
  }

  /**
   * this should be scheduled by min cache interval second time interval.
   */
  public static void scheduledClear() {
    long currentTs = System.currentTimeMillis();
    CACHES.values().forEach(c -> {
      try {
        long t = CACHE_CLEAN_TIMESTAMP.getOrDefault(c.cacheName(), 0L);
        if (TimeUnit.MILLISECONDS.toSeconds(currentTs - t) >
            c.cacheValiditySeconds()) {
          log.info("Cleaning up cache {}", c.cacheName());
          c.cleanCache();
          CACHE_CLEAN_TIMESTAMP.put(c.cacheName(), currentTs);
        }
      } catch (Exception e) {
        log.error("Error while cleaning cache {}", e.getMessage(), e);
      }
    });
  }

}
