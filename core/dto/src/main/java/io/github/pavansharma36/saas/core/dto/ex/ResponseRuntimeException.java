package io.github.pavansharma36.saas.core.dto.ex;

import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import io.github.pavansharma36.saas.utils.ex.AppRuntimeException;
import lombok.Getter;

/**
 * Exception to encapsulate runtime errors in internal api calls.
 */
public class ResponseRuntimeException extends AppRuntimeException {

  private final int status;
  @Getter
  private final ResponseObject<Object> response;

  public ResponseRuntimeException(String message, int status, ResponseObject<Object> response) {
    super(message);
    this.status = status;
    this.response = response;
  }


  @Override
  public boolean isError() {
    return status >= 500;
  }

  public ResponseObject<Object> getResponse() {
    return this.response;
  }
}
