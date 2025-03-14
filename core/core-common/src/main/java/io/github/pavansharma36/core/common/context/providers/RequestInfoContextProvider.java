package io.github.pavansharma36.core.common.context.providers;

import io.github.pavansharma36.core.common.context.AbstractJsonThreadLocalContext;
import io.github.pavansharma36.core.common.context.RequestInfo;

/**
 * class to hold request info.
 */
public final class RequestInfoContextProvider extends AbstractJsonThreadLocalContext<RequestInfo> {

  private static final RequestInfoContextProvider PROVIDER = new RequestInfoContextProvider();

  static {
    ThreadLocalContextProviders.register(PROVIDER);
  }

  private RequestInfoContextProvider() {
    super(RequestInfo.class);
  }

  public static RequestInfoContextProvider getInstance() {
    return PROVIDER;
  }
}
