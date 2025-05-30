package io.github.pavansharma36.core.common.service;

import io.github.pavansharma36.saas.core.dto.common.TenantDto;

public interface TenantService {

  TenantDto getTenantById(String id);

  void updateTenantById(String id, TenantDto tenantDto);

}
