package io.github.pavansharma36.saas.utils.ex;

public class AppConfigurationException extends AppRuntimeException {

  public AppConfigurationException(String message) {
    super(message);
  }

  public AppConfigurationException(String message, Throwable t) {
    super(message, t);
  }

  @Override
  public boolean isError() {
    return true;
  }
}
