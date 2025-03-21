package io.github.pavansharma36.saas.galaxy.api;

import io.github.pavansharma36.saas.core.dto.ResponseObject;
import io.github.pavansharma36.saas.core.dto.tenant.TenantDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("tenant")
public interface TenantApi {

  @GetMapping
  ResponseObject<TenantDTO> getTenantByIdOrName(
      @RequestParam(name = "id", required = false) String id,
      @RequestParam(name = "name", required = false) String name);

}
