package io.github.pavansharma36.saas.core.server.filter;


import io.github.pavansharma36.core.common.context.providers.MDCContextProvider;
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

@Slf4j
@WebFilter(urlPatterns = "/*")
public class AGlobalFilter extends HttpFilter {

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    long startTime = System.currentTimeMillis();
    String reqId = getRequestId(req);
    MDCContextProvider.put(Constants.REQUEST_ID_MDC_KEY, reqId);
    res.setHeader(Constants.REQUEST_ID_HEADER, reqId);
    super.doFilter(req, res, chain);

    String appName = req.getHeader(Constants.APP_NAME_HEADER);
    String appType = req.getHeader(Constants.APP_TYPE_HEADER);

    long millis = System.currentTimeMillis() - startTime;
    log.info("Request {}-{} from {}-{}, response status:{} in {} millis", req.getMethod(),
        req.getRequestURI(), appName, appType,
        res.getStatus(), millis);
  }

  private String getRequestId(HttpServletRequest request) {
    String reqId = request.getHeader(Constants.REQUEST_ID_HEADER);
    if (reqId == null) {
      reqId = Utils.randomRequestId();
      log.info("Request id header was missing, generated {}", reqId);
    }
    return reqId;
  }

}
