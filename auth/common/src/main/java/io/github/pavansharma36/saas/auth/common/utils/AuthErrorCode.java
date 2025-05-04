package io.github.pavansharma36.saas.auth.common.utils;

import io.github.pavansharma36.core.common.validation.AppValidatorFactory;
import io.github.pavansharma36.saas.utils.ex.code.ErrorCode;
import java.util.Map;

public enum AuthErrorCode implements ErrorCode {

  USERNAME_ALREADY_EXISTS(1);

  static {
    AppValidatorFactory.registerErrorCodeMessages(AuthConstants.APP_NAME, AuthErrorCode.class);
  }

  private final int index;

  AuthErrorCode(int index) {
    this.index = index;
  }

  @Override
  public String code() {
    return String.format("AUTH_%04d", this.index);
  }

  @Override
  public String message(Map<String, Object> params) {
    return AppValidatorFactory.formatMessage(name(), params);
  }
}
