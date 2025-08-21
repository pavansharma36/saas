package io.github.pavansharma36.saas.core.common.validation;

import io.github.pavansharma36.saas.utils.ex.AppRuntimeException;
import io.github.pavansharma36.saas.utils.ex.code.ErrorCode;
import java.util.Collections;
import java.util.Map;
import lombok.Getter;

@Getter
public class ErrorCodeException extends AppRuntimeException {

  private final ErrorCode errorCode;
  private final Map<String, Object> params;
  private final int status;

  public ErrorCodeException(ErrorCode errorCode, int status) {
    this(errorCode.message(), errorCode, Collections.emptyMap(), status);
  }

  public ErrorCodeException(ErrorCode errorCode, Map<String, Object> params, int status) {
    this(errorCode.message(), errorCode, params, status);
  }

  public ErrorCodeException(String message, ErrorCode errorCode, Map<String, Object> params,
                            int status) {
    super(message);
    this.errorCode = errorCode;
    this.params = params;
    this.status = status;
  }

  public ErrorCodeException(ErrorCode errorCode, Map<String, Object> params, int status,
                            Throwable cause) {
    super(cause.getMessage(), cause);
    this.errorCode = errorCode;
    this.params = params;
    this.status = status;
  }

  @Override
  public int statusCode() {
    return this.status;
  }
}
