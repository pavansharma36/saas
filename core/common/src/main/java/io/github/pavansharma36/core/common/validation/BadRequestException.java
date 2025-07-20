package io.github.pavansharma36.core.common.validation;

import io.github.pavansharma36.saas.utils.ex.code.ErrorCode;
import java.util.Map;

public class BadRequestException extends ErrorCodeException {

  public BadRequestException(ErrorCode errorCode) {
    super(errorCode, 400);
  }

  public BadRequestException(ErrorCode errorCode, Map<String, Object> params) {
    super(errorCode, params, 400);
  }
  
}
