package io.github.pavansharma36.core.common.validation;

import io.github.pavansharma36.saas.utils.ex.code.ErrorCode;
import java.util.Map;

public enum CoreErrorCode implements ErrorCode {

  BAD_REQUEST(400),
  SERVER_ERROR(500),
  FIELD_VALIDATION_ERROR(1),
  OBJECT_VALIDATION_ERROR(2),
  ;

  private final int index;

  CoreErrorCode(final int index) {
    this.index = index;
  }

  @Override
  public String code() {
    return String.format("CORE_%04d", this.index);
  }

  @Override
  public String message(Map<String, Object> params) {
    return AppValidatorFactory.formatMessage(name(), params);
  }
}
