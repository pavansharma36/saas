package io.github.pavansharma36.core.common.context.providers;

import io.github.pavansharma36.core.common.context.AbstractJsonThreadLocalContext;
import io.github.pavansharma36.core.common.context.RequestInfo;
import io.github.pavansharma36.core.common.utils.AuthSharedConstants;
import io.github.pavansharma36.saas.utils.collections.CollectionUtils;
import java.util.Optional;
import java.util.Set;

/**
 * class to hold request info.
 */
public final class RequestInfoContextProvider extends AbstractJsonThreadLocalContext<RequestInfo> {

  private static final RequestInfoContextProvider PROVIDER = new RequestInfoContextProvider();

  private RequestInfoContextProvider() {
    super(RequestInfo.class);
  }

  public static RequestInfoContextProvider getInstance() {
    return PROVIDER;
  }

  public static Optional<String> getTenantId() {
    return PROVIDER.get().map(RequestInfo::getTenantId);
  }

  public static Optional<String> getUserId() {
    return PROVIDER.get().map(RequestInfo::getUserId);
  }

  public static boolean isHostAdmin() {
    return getRoles().contains(AuthSharedConstants.Role.ROLE_HOST_ADMIN);
  }

  public static boolean isTenantAdmin() {
    return getRoles().contains(AuthSharedConstants.Role.ROLE_TENANT_ADMIN);
  }

  private static Set<String> getRoles() {
    return CollectionUtils.nullSafeSet(PROVIDER.getOrThrow().getRoles());
  }

}
