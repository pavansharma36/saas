package io.github.pavansharma36.core.common.pubsub.subscriber.handler;

import io.github.pavansharma36.core.common.cache.inmemory.InmemoryCaches;
import io.github.pavansharma36.core.common.pubsub.payload.InMemoryCacheCleanupPayload;
import org.springframework.stereotype.Component;

@Component
public class InMemoryCacheCleanupHandler
    extends AbstractSubscriptionHandler<InMemoryCacheCleanupPayload> {

  public InMemoryCacheCleanupHandler() {
    super(InMemoryCacheCleanupPayload.class);
  }

  @Override
  protected void handleImpl(InMemoryCacheCleanupPayload data) {
    if (data.getCacheKey() != null) {
      InmemoryCaches.clear(data.getCacheName(), data.getCacheKey());
    } else {
      InmemoryCaches.clear(data.getCacheName());
    }
  }

  @Override
  public String eventType() {
    return InMemoryCacheCleanupPayload.EVENT_TYPE;
  }
}
