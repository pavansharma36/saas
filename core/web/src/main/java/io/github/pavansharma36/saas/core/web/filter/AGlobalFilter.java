package io.github.pavansharma36.saas.core.web.filter;


import io.github.pavansharma36.core.common.context.RequestInfo;
import io.github.pavansharma36.core.common.context.providers.MDCContextProvider;
import io.github.pavansharma36.core.common.context.providers.RequestInfoContextProvider;
import io.github.pavansharma36.core.common.context.providers.ThreadLocalContextProviders;
import io.github.pavansharma36.saas.utils.Constants;
import io.github.pavansharma36.saas.utils.Utils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.MDC;

/**
 * AGlobal filter responsible for setting request id context and logging request.
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class AGlobalFilter extends HttpFilter {

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    long startTime = System.currentTimeMillis();
    setMdcContext(req, res);
    RequestInfo requestInfo = createRequestInfoContext(req);
    try {

      String attemptCount = req.getHeader(Constants.Header.RETRY_ATTEMPT_HEADER);
      if (NumberUtils.isCreatable(attemptCount)) {
        int retryAttempt = NumberUtils.toInt(attemptCount);
        requestInfo.setRetryAttempt(retryAttempt);

        MDCContextProvider.put(Constants.ATTEMPT_ID_MDC_KEY, requestInfo.getAttemptId());
        log.info("Retry >>> {} request received {}-{} from App: {}-{}", retryAttempt,
            req.getMethod(),
            req.getRequestURI(),
            requestInfo.getAppName(), requestInfo.getAppType());
      }
      super.doFilter(req, res, chain);

    } finally {
      long millis = System.currentTimeMillis() - startTime;
      log.info("Request {}-{} API: {} from IP: {} App: {}-{}, response status:{} in {} millis",
          req.getMethod(),
          req.getRequestURI(), MDC.get(Constants.API_MDC_KEY), requestInfo.getIp(),
          requestInfo.getAppName(),
          requestInfo.getAppType(),
          res.getStatus(), millis);
      ThreadLocalContextProviders.clearAll();
    }

  }

  private void setMdcContext(HttpServletRequest req, HttpServletResponse res) {
    String reqId = getRequestId(req);
    MDCContextProvider.put(Constants.REQUEST_ID_MDC_KEY, reqId);
    res.setHeader(Constants.Header.REQUEST_ID_HEADER, reqId);
  }

  private String getRequestId(HttpServletRequest request) {
    String reqId = request.getHeader(Constants.Header.REQUEST_ID_HEADER);
    if (reqId == null) {
      reqId = Utils.randomRequestId();
      log.info("Request id header was missing, generated {}", reqId);
    }
    return reqId;
  }

  private RequestInfo createRequestInfoContext(HttpServletRequest req) {
    String clientIp = getClientIp(req);
    String appName = req.getHeader(Constants.Header.APP_NAME_HEADER);
    String appType = req.getHeader(Constants.Header.APP_TYPE_HEADER);
    String attemptId = req.getHeader(Constants.Header.ATTEMPT_ID_HEADER);

    RequestInfo info = new RequestInfo();
    info.setIp(clientIp);
    info.setAppName(appName);
    info.setAppType(appType);
    info.setAttemptId(attemptId);
    RequestInfoContextProvider.getInstance().set(info);

    return info;
  }

  private String getClientIp(HttpServletRequest req) {
    String forwardedFor = req.getHeader(Constants.Header.FORWARDED_FOR_HEADER);
    if (forwardedFor != null) {
      String[] ips = forwardedFor.split(",");
      return ips[0];
    }
    return req.getRemoteAddr();
  }

}
