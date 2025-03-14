package io.github.pavansharma36.saas.galaxy.core.service;

import io.github.pavansharma36.saas.galaxy.dto.tenant.TenantDTO;

public interface TenantService {

  TenantDTO getTenantById(String id);

  TenantDTO getTenantByName(String name);

}
