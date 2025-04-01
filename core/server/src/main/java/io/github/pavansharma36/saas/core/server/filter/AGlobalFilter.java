package io.github.pavansharma36.saas.core.server.filter;


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

/**
 * AGlobal filter responsible for setting request id context and logging request.
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class AGlobalFilter extends HttpFilter {

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws IOException, ServletException {

    setMdcContext(req, res);
    String clientIp = getClientIp(req);
    String appName = req.getHeader(Constants.Header.APP_NAME_HEADER);
    String appType = req.getHeader(Constants.Header.APP_TYPE_HEADER);
    setRequestInfoContext(clientIp, appName, appType);
    long startTime = System.currentTimeMillis();
    super.doFilter(req, res, chain);
    long millis = System.currentTimeMillis() - startTime;

    log.info("Request {}-{} from IP:{} App:{}-{}, response status:{} in {} millis", req.getMethod(),
        req.getRequestURI(), clientIp, appName, appType,
        res.getStatus(), millis);

    ThreadLocalContextProviders.clearAll();
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

  private void setRequestInfoContext(String ip, String appName, String appType) {
    RequestInfo info = new RequestInfo();
    info.setIp(ip);
    info.setAppName(appName);
    info.setAppType(appType);
    RequestInfoContextProvider.getInstance().set(info);
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
