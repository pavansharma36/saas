package io.github.pavansharma36.saas.utils.ex;

public abstract class AppRuntimeException extends RuntimeException {
  AppRuntimeException(String message) {
    super(message);
  }

  AppRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public abstract boolean isError();
}
