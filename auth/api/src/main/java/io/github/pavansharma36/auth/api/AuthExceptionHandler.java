package io.github.pavansharma36.auth.api;

import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import javax.security.auth.login.CredentialExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AuthExceptionHandler {

  @ExceptionHandler(CredentialExpiredException.class)
  public ResponseObject<Object> handleCredentialExpiredException(
      CredentialExpiredException exception) {
    return ResponseObject.response("test", "creds expired");
  }

}
