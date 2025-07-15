package io.github.pavansharma36.core.common.validation;

import io.github.pavansharma36.saas.utils.ex.code.ErrorCode;
import java.util.Map;

public enum CoreErrorCode implements ErrorCode {

  BAD_REQUEST(400),
  UNAUTHENTICATED(401),
  UNAUTHORIZED(403),
  NOT_FOUND(404),
  SERVER_ERROR(500),

  REQUEST_BODY_REQUIRED(4000),
  VALIDATION_ERROR(4001),
  OBJECT_VALIDATION_ERROR(4002),
  INVALID_AUTHORIZATION_HEADER(4003),
  FIELD_VALIDATION_ERROR(4004),

  TENANT_NOT_FOUND(5000),
  USER_CONTEXT_NOT_SET(5001),
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
