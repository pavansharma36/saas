package io.github.pavansharma36.core.common.service;

import io.github.pavansharma36.saas.core.dto.tenant.TenantDTO;

public interface TenantService {

  TenantDTO getTenantById(String id);

  TenantDTO getTenantByName(String name);

}
