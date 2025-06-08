package io.github.pavansharma36.core.common.validation;

import io.github.pavansharma36.saas.utils.ex.AppRuntimeException;
import io.github.pavansharma36.saas.utils.ex.code.ErrorCode;
import java.util.Map;

public class BadRequestException extends AppRuntimeException {

  public BadRequestException(ErrorCode errorCode) {
    super(errorCode.message());
  }

  public BadRequestException(ErrorCode errorCode, Map<String, Object> params) {
    super(errorCode.message(params));
  }

  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  @Override
  public int statusCode() {
    return 400;
  }
}
