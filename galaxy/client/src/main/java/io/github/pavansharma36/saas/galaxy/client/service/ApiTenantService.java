package io.github.pavansharma36.saas.galaxy.client.service;

import io.github.pavansharma36.core.common.cache.AbstractInMemoryCache;
import io.github.pavansharma36.core.common.cache.InmemoryCache;
import io.github.pavansharma36.core.common.service.TenantService;
import io.github.pavansharma36.saas.core.dto.tenant.TenantDto;
import io.github.pavansharma36.saas.galaxy.api.TenantApi;
import io.github.pavansharma36.saas.galaxy.client.GalaxyClientFactory;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
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
      throw new ServerRuntimeException("Tenant not found");
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
}
