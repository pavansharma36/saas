package io.github.pavansharma36.saas.utils.ex;

public abstract class AppRuntimeException extends RuntimeException {
  protected AppRuntimeException(String message) {
    super(message);
  }

  protected AppRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public abstract boolean isError();

  public int statusCode() {
    return 500;
  }
}
