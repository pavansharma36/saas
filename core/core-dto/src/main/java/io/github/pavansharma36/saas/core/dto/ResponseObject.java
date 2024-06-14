package io.github.pavansharma36.saas.core.dto;

import io.github.pavansharma36.saas.utils.Constants;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.MDC;

@Getter
@Builder
public class ResponseObject<T> {
  private final boolean success;
  private final String reqId = MDC.get(Constants.REQUEST_ID_MDC_KEY);
  private final T data;
  private final List<Message> messages;

  public static <M> ResponseObject<M> success() {
    return success(null);
  }

  public static <M> ResponseObject<M> success(M data) {
    return success(data, null);
  }

  public static <M> ResponseObject<M> success(M data, List<Message> messages) {
    return (ResponseObject<M>) ResponseObject.builder().success(true)
        .data(data)
        .messages(messages)
        .build();
  }
}
