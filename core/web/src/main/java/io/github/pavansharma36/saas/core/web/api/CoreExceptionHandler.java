package io.github.pavansharma36.saas.core.web.api;

import io.github.pavansharma36.saas.core.dto.ResponseObject;
import io.github.pavansharma36.saas.utils.Constants;
import io.github.pavansharma36.saas.utils.ex.AppRuntimeException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CoreExceptionHandler {

  @ExceptionHandler(AppRuntimeException.class)
  public ResponseObject<Object> handleRuntimeException(
      HttpServletRequest request,
      HttpServletResponse response,
      AppRuntimeException appRuntimeException) {
    if (appRuntimeException.isError()) {
      log.error("Error while processing {}", request.getRequestURI(), appRuntimeException);
    } else {
      log.error("exception while processing {}", request.getRequestURI(), appRuntimeException);
    }
    response.setStatus(appRuntimeException.statusCode());
    return ResponseObject.builder().reqId(MDC.get(Constants.REQUEST_ID_MDC_KEY)).build();
  }

  @ExceptionHandler(Exception.class)
  public ResponseObject<Object> handleException(
      HttpServletRequest request,
      HttpServletResponse response,
      Exception appRuntimeException) {
    log.error("Error while processing {}", request.getRequestURI(), appRuntimeException);
    response.setStatus(500);
    return ResponseObject.builder().reqId(MDC.get(Constants.REQUEST_ID_MDC_KEY)).build();
  }
}
