package io.github.pavansharma36.saas.galaxy.client.service;

import io.github.pavansharma36.saas.galaxy.api.TenantApi;
import io.github.pavansharma36.saas.galaxy.client.GalaxyClientFactory;
import io.github.pavansharma36.saas.galaxy.core.service.TenantService;
import io.github.pavansharma36.saas.galaxy.dto.tenant.TenantDTO;

public class TenantApiService implements TenantService {

  private final TenantApi tenantApi = GalaxyClientFactory.tenantApi();

  @Override
  public TenantDTO getTenantById(String id) {
    return tenantApi.getTenantByIdOrName(id, null).getData();
  }

  @Override
  public TenantDTO getTenantByName(String name) {
    return tenantApi.getTenantByIdOrName(null, name).getData();
  }
}
