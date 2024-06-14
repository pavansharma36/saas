package io.github.pavansharma36.saas.core.server.filter;


import io.github.pavansharma36.core.common.context.providers.MDCContextProvider;
import io.github.pavansharma36.saas.utils.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class AGlobalFilter extends HttpFilter {

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    String reqId = getRequestId(req);
    MDCContextProvider.put(Constants.REQUEST_ID_MDC_KEY, reqId);
    res.setHeader(Constants.REQUEST_ID_HEADER, reqId);
    super.doFilter(req, res, chain);
  }

  private String getRequestId(HttpServletRequest request) {
    String reqId = request.getHeader(Constants.REQUEST_ID_HEADER);
    if (reqId == null) {
      reqId = RandomStringUtils.randomAlphanumeric(12);
      log.info("Request id header was missing, generated {}", reqId);
    }
    return reqId;
  }

}
