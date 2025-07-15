package io.github.pavansharma36.core.common.validation;

import io.github.pavansharma36.saas.utils.ex.AppRuntimeException;
import io.github.pavansharma36.saas.utils.ex.code.ErrorCode;
import java.util.Collections;
import java.util.Map;
import lombok.Getter;

@Getter
public class ErrorCodeException extends AppRuntimeException {

  private final ErrorCode errorCode;
  private final Map<String, Object> params;

  public ErrorCodeException(ErrorCode errorCode) {
    this(errorCode.message(), errorCode, Collections.emptyMap());
  }

  public ErrorCodeException(ErrorCode errorCode, Map<String, Object> params) {
    this(errorCode.message(), errorCode, params);
  }

  public ErrorCodeException(String message, ErrorCode errorCode, Map<String, Object> params) {
    super(message);
    this.errorCode = errorCode;
    this.params = params;
  }

  public ErrorCodeException(ErrorCode errorCode, Map<String, Object> params,
                            Throwable cause) {
    super(cause.getMessage(), cause);
    this.errorCode = errorCode;
    this.params = params;
  }


}
