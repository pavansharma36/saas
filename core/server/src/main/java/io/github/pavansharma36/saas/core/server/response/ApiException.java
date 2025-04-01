package io.github.pavansharma36.saas.core.server.response;

import io.github.pavansharma36.saas.core.dto.Message;
import java.util.List;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

  private final int status;
  private final List<Message> messages;

  public ApiException(String message) {
    super(message);
    this.status = 500;
    this.messages = null;
  }

  public ApiException(String message, int status) {
    super(message);
    this.status = status;
    this.messages = null;
  }

  public ApiException(String message, Throwable cause, int status) {
    super(message, cause);
    this.status = status;
    this.messages = null;
  }

  public ApiException(String message, Throwable cause, int status, List<Message> messages) {
    super(message, cause);
    this.status = status;
    this.messages = messages;
  }
}
