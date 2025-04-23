package io.github.pavansharma36.core.common.context.providers;

import io.github.pavansharma36.core.common.context.TenantContext;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;

public class TenantContextProvider {

  private static TenantContext tenantContext;

  public static void register(TenantContext tenantContext) {
    if (tenantContext != null) {
      throw new ServerRuntimeException("Tenant context already registered");
    }
    TenantContextProvider.tenantContext = tenantContext;
  }

  public static TenantContext getInstance() {
    if (tenantContext == null) {
      throw new ServerRuntimeException("Tenant context not initialized");
    }
    return tenantContext;
  }

}
