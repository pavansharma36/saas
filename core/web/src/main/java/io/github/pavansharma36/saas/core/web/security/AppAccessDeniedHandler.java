package io.github.pavansharma36.saas.core.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pavansharma36.core.common.validation.CoreErrorCode;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
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

    ResponseObject<Object> res = ResponseObject.response(CoreErrorCode.UNAUTHORIZED.code(),
        CoreErrorCode.UNAUTHORIZED.message(Collections.singletonMap("uri", arg0.getRequestURI())));
    arg1.setStatus(403);
    arg1.setContentType("application/json");
    mapper.writeValue(arg1.getWriter(), res);
  }

}
