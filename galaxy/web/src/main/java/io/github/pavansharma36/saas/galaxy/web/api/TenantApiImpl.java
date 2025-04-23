package io.github.pavansharma36.saas.galaxy.web.api;

import io.github.pavansharma36.saas.core.dto.common.TenantDto;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import io.github.pavansharma36.saas.galaxy.api.TenantApi;
import io.github.pavansharma36.saas.galaxy.common.service.GalaxyTenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TenantApiImpl implements TenantApi {

  private final GalaxyTenantService tenantService;

  @Override
  public ResponseObject<TenantDto> getTenant(String id) {
    return ResponseObject.response(tenantService.getTenantById(id));
  }

  @Override
  public ResponseObject<String> createTenant(TenantDto tenantDto) {
    return ResponseObject.response(tenantService.createTenant(tenantDto));
  }

  @Override
  public ResponseObject<Object> updateTenant(String tenantId, TenantDto tenantDto) {
    tenantService.updateTenantById(tenantId, tenantDto);
    return ResponseObject.empty();
  }
}
