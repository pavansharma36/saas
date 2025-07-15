package io.github.pavansharma36.saas.core.web.api;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.core.common.context.providers.MDCContextProvider;
import io.github.pavansharma36.core.common.context.providers.RequestInfoContextProvider;
import io.github.pavansharma36.core.common.utils.CoreUtils;
import io.github.pavansharma36.saas.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

public class CoreApiRequestInterceptor implements HandlerInterceptor {

  private final boolean apiInMDC = Config.getBoolean("web.api.pattern.mdc.enabled", false);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (apiInMDC) {
      String attributeName =
          (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
      MDCContextProvider.put(Constants.API_MDC_KEY, attributeName);
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) throws Exception {
    String resJwt = RequestInfoContextProvider.getInstance().getOrThrow().getResponseJwt();
    if (resJwt != null) {
      response.setHeader(Constants.Header.AUTHORIZATION_HEADER,
          CoreUtils.authorizationHeader(Constants.AUTHORIZATION_TYPE_BEARER, resJwt));
    }
  }
}
