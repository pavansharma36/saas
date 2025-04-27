package io.github.pavansharma36.saas.utils.ex;

public abstract class AppException extends Exception {

  protected AppException(String message) {
    super(message);
  }

  protected AppException(String message, Throwable cause) {
    super(message, cause);
  }

  public boolean isError() {
    return statusCode() == 500;
  }

  public int statusCode() {
    return 500;
  }

}
