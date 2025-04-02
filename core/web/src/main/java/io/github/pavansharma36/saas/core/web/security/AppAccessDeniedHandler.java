package io.github.pavansharma36.saas.core.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppAccessDeniedHandler implements AccessDeniedHandler {

  private final ObjectMapper mapper = JsonUtils.mapper();

  @Override
  public void handle(final HttpServletRequest arg0, final HttpServletResponse arg1,
                     final AccessDeniedException arg2)
      throws IOException, ServletException {
    log.warn("Access denied, Requested Url : {}", arg0.getRequestURI());
    Map<String, Object> body = HashMap.newHashMap(2);
    body.put("status", 403);
    body.put("message", "Access denied " + arg0.getRequestURI());
    arg1.setStatus(403);
    arg1.setContentType("application/json");
    mapper.writeValue(arg1.getWriter(), body);
  }

}
