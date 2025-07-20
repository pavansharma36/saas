package io.github.pavansharma36.saas.search.api;

import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import io.github.pavansharma36.saas.search.dto.TenantDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("tenant")
public interface TenantApi {

  @PutMapping
  ResponseObject<Object> saveTenant(@RequestBody @Valid TenantDto tenantDto);

}
