package io.github.pavansharma36.saas.utils.ex;

public abstract class AppException extends Exception {

  AppException(String message) {
    super(message);
  }

  AppException(String message, Throwable cause) {
    super(message, cause);
  }

  public abstract String getUIMessage();

  public abstract boolean isError();

}
