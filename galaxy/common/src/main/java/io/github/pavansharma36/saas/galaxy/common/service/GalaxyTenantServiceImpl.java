package io.github.pavansharma36.saas.galaxy.common.service;

import io.github.pavansharma36.core.common.cache.AbstractInMemoryCache;
import io.github.pavansharma36.core.common.context.TenantContext;
import io.github.pavansharma36.core.common.context.providers.TenantContextProvider;
import io.github.pavansharma36.core.common.mutex.bean.DefaultLock;
import io.github.pavansharma36.core.common.mutex.bean.LockInfo;
import io.github.pavansharma36.core.common.mutex.bean.LockType;
import io.github.pavansharma36.core.common.mutex.service.LockService;
import io.github.pavansharma36.core.common.pubsub.payload.InMemoryCacheCleanupPayload;
import io.github.pavansharma36.core.common.pubsub.payload.Payload;
import io.github.pavansharma36.core.common.pubsub.publisher.PublisherManager;
import io.github.pavansharma36.saas.core.dto.common.TenantDto;
import io.github.pavansharma36.saas.utils.Utils;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import jakarta.annotation.PostConstruct;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GalaxyTenantServiceImpl extends AbstractInMemoryCache<TenantDto>
    implements GalaxyTenantService {

  private final PublisherManager publisher;
  private final LockService lockService;

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
    log.info("trying to acquire lock on create tenant");
    try (LockInfo lock = lockService.acquireLock(DefaultLock.builder()
        .name("createTenant")
        .type(LockType.FIXED)
        .maxCount(10)
        .duration(Duration.ofMinutes(2))
        .build()).orElseThrow(() -> new ServerRuntimeException("Couldn't acquire lock"))) {
      return Utils.randomRequestId();
    }
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

  @PostConstruct
  public void postConstruct() {
    TenantContextProvider.register(new TenantContext(this));
  }
}
