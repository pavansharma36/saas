package io.github.pavansharma36.core.common.validation;

import java.util.Collections;

public class ServerRuntimeException extends ErrorCodeException {

  public ServerRuntimeException(String message) {
    super(message, CoreErrorCode.SERVER_ERROR, Collections.emptyMap());
  }

  public ServerRuntimeException(Throwable cause) {
    super(CoreErrorCode.SERVER_ERROR, Collections.emptyMap(), cause);
  }

}
