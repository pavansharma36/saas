package io.github.pavansharma36.saas.core.web.api;

import io.github.pavansharma36.core.common.context.providers.MDCContextProvider;
import io.github.pavansharma36.saas.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

public class CoreApiRequestInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String attributeName =
        (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
    MDCContextProvider.put(Constants.API_MDC_KEY, attributeName);
    return true;
  }
}
