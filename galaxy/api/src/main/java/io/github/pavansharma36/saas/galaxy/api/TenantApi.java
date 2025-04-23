package io.github.pavansharma36.saas.galaxy.api;

import io.github.pavansharma36.saas.core.dto.common.TenantDto;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("tenant")
public interface TenantApi {

  @GetMapping("{id}")
  ResponseObject<TenantDto> getTenant(
      @PathVariable(name = "id") String id);

  @PostMapping
  ResponseObject<String> createTenant(@RequestBody @Valid TenantDto tenantDto);

  @PutMapping("/{tenantId}")
  ResponseObject<Object> updateTenant(@PathVariable("tenantId") String tenantId,
                                      TenantDto tenantDto);

}
