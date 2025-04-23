package io.github.pavansharma36.saas.galaxy.common.service;

import io.github.pavansharma36.core.common.service.TenantService;
import io.github.pavansharma36.saas.core.dto.common.TenantDto;

public interface GalaxyTenantService extends TenantService {

  String createTenant(TenantDto tenantDto);

}
