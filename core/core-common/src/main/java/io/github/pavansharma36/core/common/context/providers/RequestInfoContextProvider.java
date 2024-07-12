package io.github.pavansharma36.core.common.context.providers;

import io.github.pavansharma36.core.common.context.AbstractJsonThreadLocalContext;
import io.github.pavansharma36.core.common.context.RequestInfo;

public class RequestInfoContextProvider extends AbstractJsonThreadLocalContext<RequestInfo> {

  protected RequestInfoContextProvider() {
    super(RequestInfo.class);
  }
}
