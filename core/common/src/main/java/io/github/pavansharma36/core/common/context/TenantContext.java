package io.github.pavansharma36.core.common.context;

import io.github.pavansharma36.core.common.service.TenantService;
import io.github.pavansharma36.saas.core.dto.common.TenantDto;
import java.nio.charset.StandardCharsets;

public class TenantContext extends AbstractThreadLocalContext<TenantDto> {

  private final TenantService tenantService;

  public TenantContext(TenantService tenantService) {
    super();
    this.tenantService = tenantService;
  }

  /**
   * for tenant context serialize only id.
   *
   * @return - tenant id.
   */
  @Override
  public byte[] toByteArray() {
    return get().map(t -> t.getId().getBytes(StandardCharsets.UTF_8)).orElseGet(() -> new byte[0]);
  }

  /**
   * create tenant context from id.
   *
   * @param value - byte  array returned from toByteArray.
   */
  @Override
  public void setFromByteArray(byte[] value) {
    String id = new String(value, StandardCharsets.UTF_8);
    set(tenantService.getTenantById(id));
  }
}
