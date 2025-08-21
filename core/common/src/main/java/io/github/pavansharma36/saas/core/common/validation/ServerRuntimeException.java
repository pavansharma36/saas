package io.github.pavansharma36.saas.core.common.validation;

import java.util.Collections;

public class ServerRuntimeException extends ErrorCodeException {

  public ServerRuntimeException(String message) {
    super(message, CoreErrorCode.SERVER_ERROR, Collections.emptyMap(), 500);
  }

  public ServerRuntimeException(Throwable cause) {
    super(CoreErrorCode.SERVER_ERROR, Collections.emptyMap(), 500, cause);
  }

}
