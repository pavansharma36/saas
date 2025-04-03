package io.github.pavansharma36.saas.auth.web.security;

import io.github.pavansharma36.saas.core.dto.Message;
import io.github.pavansharma36.saas.core.dto.ResponseObject;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LogInFailureHandler implements AuthenticationFailureHandler {

  private static final Logger logger = LoggerFactory.getLogger(LogInFailureHandler.class);

  @Override
  public void onAuthenticationFailure(final HttpServletRequest arg0, final HttpServletResponse arg1,
                                      final AuthenticationException arg2)
      throws IOException, ServletException {
    logger.info("LogInFailure : IP:{}, Details:{}", arg0.getRemoteAddr(), arg2.getMessage());
    arg1.setContentType("application/json");
    arg1.setStatus(400);
    JsonUtils.mapper().writeValue(arg1.getWriter(),
        ResponseObject.response(null,
            Collections.singletonList(new Message(Message.Severity.ERROR,
                "Login Failed", "Invalid Credentials"))));
  }

}
