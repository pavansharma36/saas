package io.github.pavansharma36.saas.core.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pavansharma36.core.common.validation.CoreErrorCode;
import io.github.pavansharma36.saas.core.dto.ResponseObject;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper mapper = JsonUtils.mapper();

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
                       AuthenticationException authException) throws IOException, ServletException {
    log.info("Unauthenticated, Requested Url : {}", request.getRequestURI());
    response.setStatus(401);
    response.setContentType("application/json");
    mapper.writeValue(response.getWriter(),
        ResponseObject.response(CoreErrorCode.UNAUTHENTICATED.code(),
            CoreErrorCode.UNAUTHENTICATED.message()));
  }
}
