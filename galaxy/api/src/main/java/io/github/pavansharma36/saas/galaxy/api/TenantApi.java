package io.github.pavansharma36.saas.galaxy.api;

import io.github.pavansharma36.saas.core.dto.ResponseObject;
import io.github.pavansharma36.saas.core.dto.tenant.TenantDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("tenant")
public interface TenantApi {

  @GetMapping("{id}")
  ResponseObject<TenantDto> getTenant(
      @PathVariable(name = "id") String id);

  @PostMapping
  ResponseObject<Object> createTenant(@Valid TenantDto tenantDto);

}
