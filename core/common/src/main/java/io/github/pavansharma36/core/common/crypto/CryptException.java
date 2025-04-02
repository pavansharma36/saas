package io.github.pavansharma36.core.common.crypto;

import io.github.pavansharma36.saas.utils.ex.AppRuntimeException;

/**
 * Exception to throw on any cryptography errors.
 */
public class CryptException extends AppRuntimeException {
  public CryptException(String message) {
    super(message);
  }

  public CryptException(String message, Throwable cause) {
    super(message, cause);
  }

  @Override
  public boolean isError() {
    return true;
  }
}
