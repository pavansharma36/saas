package io.github.pavansharma36.saas.core.web.api;

import com.fasterxml.jackson.core.JsonParseException;
import io.github.pavansharma36.core.common.validation.CoreErrorCode;
import io.github.pavansharma36.saas.core.dto.Message;
import io.github.pavansharma36.saas.core.dto.ResponseObject;
import io.github.pavansharma36.saas.utils.ex.AppRuntimeException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
      log.error("Error while processing {}: {}", request.getRequestURI(),
          appRuntimeException.getMessage());
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
      if (e instanceof FieldError) {
        m.field(((FieldError) e).getField());
        m.summary(
            CoreErrorCode.FIELD_VALIDATION_ERROR.message(
                Collections.singletonMap("field", ((FieldError) e).getField())));
      } else {
        m.summary(
            CoreErrorCode.OBJECT_VALIDATION_ERROR.message(
                Collections.singletonMap("object", e.getObjectName())));
      }
      messages.add(m.build());
    });

    return ResponseObject.response(null, messages, CoreErrorCode.BAD_REQUEST.code(),
        CoreErrorCode.BAD_REQUEST.message());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseObject<Object> handleMessageNotReadable(HttpServletRequest request,
                                                         HttpServletResponse response,
                                                         HttpMessageNotReadableException exception) {
    log.info("exception while processing {} {}", request.getRequestURI(), exception.getMessage());
    Message m;
    if (exception.getCause() instanceof JsonParseException) {
      m = Message.builder().severity(Message.Severity.ERROR).summary("Invalid content")
          .detail(exception.getMessage()).build();
    } else {
      m = Message.builder().severity(Message.Severity.ERROR).summary("Bad Request")
          .detail("Request body is required").build();
    }
    return ResponseObject.response(null, Collections.singletonList(m));
  }


  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ResponseObject<Object> handleException(
      HttpServletRequest request,
      HttpServletResponse response,
      Exception appRuntimeException) {
    log.error("Error while processing {}", request.getRequestURI(), appRuntimeException);
    return ResponseObject.empty();
  }
}
