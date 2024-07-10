package io.github.pavansharma36.saas.utils.ex;

public class ServerRuntimeException extends AppRuntimeException {

  public ServerRuntimeException(String message) {
    super(message);
  }

  public ServerRuntimeException(Throwable cause) {
    super(cause.getMessage(), cause);
  }

  @Override
  public String getUIMessage() {
    return getMessage();
  }

  @Override
  public boolean isError() {
    return true;
  }
}
