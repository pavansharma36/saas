package io.github.pavansharma36.saas.galaxy.client.service;

import io.github.pavansharma36.core.common.service.TenantService;
import io.github.pavansharma36.saas.core.dto.tenant.TenantDTO;
import io.github.pavansharma36.saas.galaxy.api.TenantApi;
import io.github.pavansharma36.saas.galaxy.client.GalaxyClientFactory;

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
