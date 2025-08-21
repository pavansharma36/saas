package io.github.pavansharma36.saas.galaxy.client.service;

import io.github.pavansharma36.saas.core.common.cache.inmemory.AbstractInMemoryCache;
import io.github.pavansharma36.saas.core.common.cache.inmemory.InmemoryCache;
import io.github.pavansharma36.saas.core.common.context.TenantContext;
import io.github.pavansharma36.saas.core.common.context.providers.TenantContextProvider;
import io.github.pavansharma36.saas.core.common.service.TenantService;
import io.github.pavansharma36.saas.core.common.validation.CoreErrorCode;
import io.github.pavansharma36.saas.core.common.validation.ServerRuntimeException;
import io.github.pavansharma36.saas.core.dto.common.TenantDto;
import io.github.pavansharma36.saas.galaxy.api.TenantApi;
import io.github.pavansharma36.saas.galaxy.client.GalaxyClientFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class ApiTenantService extends AbstractInMemoryCache<TenantDto>
    implements TenantService, InmemoryCache {

  private final TenantApi tenantApi = GalaxyClientFactory.tenantApi();

  @Override
  public TenantDto getTenantById(String id) {
    TenantDto tenantDto = CACHE.get(id);
    if (tenantDto != null) {
      return tenantDto;
    }
    tenantDto = tenantApi.getTenant(id).getData();
    if (tenantDto == null) {
      throw new ServerRuntimeException(CoreErrorCode.TENANT_NOT_FOUND.message());
    }
    CACHE.put(id, tenantDto);
    return tenantDto;
  }

  @Override
  public void updateTenantById(String id, TenantDto tenantDto) {
    tenantApi.updateTenant(id, tenantDto);
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
