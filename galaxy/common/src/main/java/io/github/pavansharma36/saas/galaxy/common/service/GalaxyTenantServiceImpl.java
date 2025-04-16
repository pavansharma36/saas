package io.github.pavansharma36.saas.galaxy.common.service;

import io.github.pavansharma36.core.common.cache.AbstractInMemoryCache;
import io.github.pavansharma36.core.common.pubsub.payload.InMemoryCacheCleanupPayload;
import io.github.pavansharma36.core.common.pubsub.payload.Payload;
import io.github.pavansharma36.core.common.pubsub.publisher.PublisherManager;
import io.github.pavansharma36.saas.core.dto.tenant.TenantDto;
import io.github.pavansharma36.saas.utils.Utils;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GalaxyTenantServiceImpl extends AbstractInMemoryCache<TenantDto>
    implements GalaxyTenantService {

  private final PublisherManager publisher;

  @Override
  public TenantDto getTenantById(String id) {
    TenantDto tenantDto = CACHE.get(id);
    if (tenantDto != null) {
      return tenantDto;
    }
    if ("test".equals(id)) {
      tenantDto = new TenantDto();
      tenantDto.setCode("TEST");
      tenantDto.setId("test");
      tenantDto.setName("Test Tenant");
    }
    if (tenantDto == null) {
      throw new ServerRuntimeException("Tenant not found");
    }
    CACHE.put(id, tenantDto);
    return tenantDto;
  }

  @Override
  public String createTenant(TenantDto tenantDto) {
    return Utils.randomRequestId();
  }

  @Override
  public void updateTenantById(String id, TenantDto tenantDto) {
    publisher.publish(Payload.builder()
        .eventType(InMemoryCacheCleanupPayload.EVENT_TYPE)
        .data(InMemoryCacheCleanupPayload.builder()
            .cacheName(TenantDto.CACHE_NAME).cacheKey(id)
            .build())
        .build());
  }

  @Override
  public String cacheName() {
    return TenantDto.CACHE_NAME;
  }

  @Override
  public int cacheValiditySeconds() {
    return 900;
  }
}
