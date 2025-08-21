package io.github.pavansharma36.saas.auth.web.security;

import io.github.pavansharma36.saas.core.common.validation.CoreErrorCode;
import io.github.pavansharma36.saas.auth.common.utils.AuthErrorCode;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Slf4j
public class LogInFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(final HttpServletRequest arg0, final HttpServletResponse arg1,
                                      final AuthenticationException ex)
      throws IOException, ServletException {
    log.info("LogInFailure : IP:{}, Details:{}", arg0.getRemoteAddr(), ex.getMessage());
    arg1.setContentType("application/json");
    arg1.setStatus(400);

    ResponseObject<Object> responseObject = switch (ex) {
      case BadCredentialsException ignored ->
          ResponseObject.response(AuthErrorCode.BAD_CREDENTIAL.code(),
              AuthErrorCode.BAD_CREDENTIAL.message());
      case AccountExpiredException ignored ->
          ResponseObject.response(AuthErrorCode.USER_ACCOUNT_EXPIRED.code(),
              AuthErrorCode.USER_ACCOUNT_EXPIRED.message());
      case LockedException ignored ->
          ResponseObject.response(AuthErrorCode.USER_ACCOUNT_LOCKED.code(),
              AuthErrorCode.USER_ACCOUNT_LOCKED.message());
      case DisabledException ignored ->
          ResponseObject.response(AuthErrorCode.USER_ACCOUNT_DISABLED.code(),
              AuthErrorCode.USER_ACCOUNT_DISABLED.message());
      case CredentialsExpiredException ignored ->
          ResponseObject.response(AuthErrorCode.USER_ACCOUNT_CREDENTIAL_EXPIRED.code(),
              AuthErrorCode.USER_ACCOUNT_CREDENTIAL_EXPIRED.message());
      default -> {
        log.error("Unhandled exception on Log in failure handler {}", ex.getMessage(), ex);
        arg1.setStatus(500);
        yield ResponseObject.response(CoreErrorCode.SERVER_ERROR.code(),
            CoreErrorCode.SERVER_ERROR.message());
      }
    };

    JsonUtils.mapper().writeValue(arg1.getWriter(), responseObject);
  }

}
