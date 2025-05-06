package io.github.pavansharma36.core.common.context.providers;

import io.github.pavansharma36.core.common.context.TenantContext;
import io.github.pavansharma36.saas.core.dto.common.TenantDto;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import java.util.Optional;
import java.util.function.Supplier;

public class TenantContextProvider {

  private static TenantContext tenantContext;

  public static void register(TenantContext tenantContext) {
    if (TenantContextProvider.tenantContext != null) {
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

  public static boolean isInitialized() {
    return tenantContext != null;
  }

  public static <T> T executeOnTenantContext(TenantDto tenant, Supplier<T> function) {
    Optional<TenantDto> currentContext = getInstance().get();
    try {
      return function.get();
    } finally {
      getInstance().clearContext();
      currentContext.ifPresent(tenantDto -> getInstance().set(tenantDto));
    }
  }

}
