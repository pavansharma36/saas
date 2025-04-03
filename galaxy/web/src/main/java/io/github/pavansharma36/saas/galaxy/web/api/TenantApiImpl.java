package io.github.pavansharma36.saas.galaxy.web.api;

import io.github.pavansharma36.saas.core.dto.ResponseObject;
import io.github.pavansharma36.saas.core.dto.tenant.TenantDto;
import io.github.pavansharma36.saas.galaxy.api.TenantApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TenantApiImpl implements TenantApi {
  @Override
  public ResponseObject<TenantDto> getTenant(String id) {
    return ResponseObject.empty();
  }

  @Override
  public ResponseObject<Object> createTenant(TenantDto tenantDto) {
    return ResponseObject.empty();
  }
}
