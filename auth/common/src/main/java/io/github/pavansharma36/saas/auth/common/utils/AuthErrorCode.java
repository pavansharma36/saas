package io.github.pavansharma36.saas.auth.common.utils;

import io.github.pavansharma36.core.common.validation.AppValidatorFactory;
import io.github.pavansharma36.saas.utils.ex.code.ErrorCode;
import java.util.Map;

public enum AuthErrorCode implements ErrorCode {

  USERNAME_ALREADY_EXISTS(1),
  USER_ACCOUNT_DISABLED(2),
  USER_ACCOUNT_EXPIRED(3),
  USER_ACCOUNT_CREDENTIAL_EXPIRED(4),
  USER_ACCOUNT_LOCKED(5),
  BAD_CREDENTIAL(6),
  ;

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
