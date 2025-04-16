package io.github.pavansharma36.core.common.pubsub.payload;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class InMemoryCacheCleanupPayload implements Serializable {

  public static final String EVENT_TYPE = "in-memory-cache-cleanup";

  private String cacheName;
  private String cacheKey;
}
