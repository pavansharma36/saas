package io.github.pavansharma36.saas.galaxy.client.service;

import io.github.pavansharma36.core.common.service.TenantService;
import io.github.pavansharma36.saas.core.dto.tenant.TenantDto;
import io.github.pavansharma36.saas.galaxy.api.TenantApi;
import io.github.pavansharma36.saas.galaxy.client.GalaxyClientFactory;
import org.springframework.stereotype.Service;

@Service
public class TenantApiService implements TenantService {

  private final TenantApi tenantApi = GalaxyClientFactory.tenantApi();

  @Override
  public TenantDto getTenantById(String id) {
    return tenantApi.getTenant(id).getData();
  }

}
