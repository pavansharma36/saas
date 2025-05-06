package io.github.pavansharma36.saas.core.web.api;

import com.fasterxml.jackson.core.JsonParseException;
import io.github.pavansharma36.core.common.validation.CoreErrorCode;
import io.github.pavansharma36.core.common.validation.ValidationException;
import io.github.pavansharma36.saas.core.dto.ex.ResponseRuntimeException;
import io.github.pavansharma36.saas.core.dto.response.Message;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import io.github.pavansharma36.saas.utils.ex.AppRuntimeException;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CoreExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ValidationException.class)
  public ResponseObject<Object> handleValidationException(ValidationException exception) {
    log.debug("Validation exception {}", exception.getMessage(), exception);
    return ResponseObject.response(null,
        exception.getErrorCodes().stream().map(errorCodeDetails ->
                Message.builder().field(errorCodeDetails.getField())
                    .summary(CoreErrorCode.FIELD_VALIDATION_ERROR.message(errorCodeDetails.getParams()))
                    .detail(errorCodeDetails.getErrorCode().message(errorCodeDetails.getParams()))
                    .severity(Message.Severity.ERROR).build())
            .toList(), CoreErrorCode.VALIDATION_ERROR.code(),
        exception.getMessage());
  }

  @ExceptionHandler(ResponseRuntimeException.class)
  public ResponseObject<Object> handleResponseException(
      HttpServletResponse response,
      ResponseRuntimeException exception) {
    response.setStatus(exception.statusCode());
    return exception.getResponse();
  }

  @ExceptionHandler(ServerRuntimeException.class)
  public ResponseObject<Object> handleServerRuntimeException(
      HttpServletRequest request,
      HttpServletResponse response,
      ServerRuntimeException exception) {
    log.error("Error while processing {}: {}", request.getRequestURI(),
        exception.getMessage(), exception);
    response.setStatus(exception.statusCode());
    return ResponseObject.response(CoreErrorCode.SERVER_ERROR.code(),
        CoreErrorCode.SERVER_ERROR.message());
  }

  @ExceptionHandler(AppRuntimeException.class)
  public ResponseObject<Object> handleRuntimeException(
      HttpServletRequest request,
      HttpServletResponse response,
      AppRuntimeException appRuntimeException) {
    if (appRuntimeException.isError()) {
      log.error("Error while processing {}: {}", request.getRequestURI(),
          appRuntimeException.getMessage(), appRuntimeException);
    } else {
      log.error("exception while processing {}: {}", request.getRequestURI(),
          appRuntimeException.getMessage());
    }
    response.setStatus(appRuntimeException.statusCode());
    return ResponseObject.empty();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseObject<Object> handleMethodArgNotValidException(
      HttpServletRequest request,
      HttpServletResponse response,
      MethodArgumentNotValidException exception) {
    log.info("exception while processing {} {}", request.getRequestURI(), exception.getMessage());
    List<Message> messages = new LinkedList<>();
    exception.getBindingResult().getAllErrors().forEach(e -> {
      Message.MessageBuilder m = Message.builder()
          .severity(Message.Severity.ERROR)
          .detail(e.getDefaultMessage());
      Map<String, Object> params = new HashMap<>(2);
      params.put("objectName", e.getObjectName());
      if (e instanceof FieldError) {
        m.field(((FieldError) e).getField());
        params.put("field", ((FieldError) e).getField());
        m.summary(
            CoreErrorCode.FIELD_VALIDATION_ERROR.message(params));
      } else {
        m.summary(
            CoreErrorCode.OBJECT_VALIDATION_ERROR.message(params));
      }
      messages.add(m.build());
    });

    return ResponseObject.response(null, messages, CoreErrorCode.VALIDATION_ERROR.code(),
        CoreErrorCode.VALIDATION_ERROR.message());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseObject<Object> handleMessageNotReadable(HttpServletRequest request,
                                                         HttpServletResponse response,
                                                         HttpMessageNotReadableException exception) {
    log.info("exception while processing {} {}", request.getRequestURI(), exception.getMessage());
    String errorCode = CoreErrorCode.BAD_REQUEST.code();
    String message;
    if (exception.getCause() instanceof JsonParseException) {
      message = exception.getMessage();
    } else {
      message = CoreErrorCode.REQUEST_BODY_REQUIRED.message();
    }
    return ResponseObject.response(errorCode, message);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MissingRequestValueException.class)
  public ResponseObject<Object> handleMissingRequestValue(MissingRequestValueException exception) {
    return ResponseObject.response(CoreErrorCode.BAD_REQUEST.code(), exception.getMessage());
  }

  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseObject<Object> handleAccessDenied(AccessDeniedException exception) {
    return ResponseObject.response(CoreErrorCode.UNAUTHORIZED.code(),
        CoreErrorCode.UNAUTHORIZED.message());
  }


  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ResponseObject<Object> handleException(
      HttpServletRequest request,
      HttpServletResponse response,
      Exception appRuntimeException) {
    log.error("Error while processing {}", request.getRequestURI(), appRuntimeException);
    return ResponseObject.response(CoreErrorCode.SERVER_ERROR.code(),
        CoreErrorCode.SERVER_ERROR.message());
  }
}
