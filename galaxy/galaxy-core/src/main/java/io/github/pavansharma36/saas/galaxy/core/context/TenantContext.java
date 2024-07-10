package io.github.pavansharma36.saas.galaxy.core.context;

import io.github.pavansharma36.core.common.context.AbstractThreadLocalContext;
import io.github.pavansharma36.saas.galaxy.dto.tenant.TenantDTO;

public class TenantContext extends AbstractThreadLocalContext<TenantDTO> {
  @Override
  public byte[] toByteArray() {
    return new byte[0];
  }

  @Override
  public void setFromByteArray(byte[] value) {

  }
}
