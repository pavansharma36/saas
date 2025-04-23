package io.github.pavansharma36.saas.core.dto.response;

import io.github.pavansharma36.saas.utils.Constants;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.MDC;

@Getter
@Builder
@Jacksonized
public class ResponseObject<T> {

  private final String reqId;
  private final T data;
  private final List<Message> messages;
  private final String errorCode;
  private final String message;

  public static <M> ResponseObject<M> empty() {
    return response(null);
  }

  public static <M> ResponseObject<M> response(M data) {
    return response(data, null);
  }

  public static <M> ResponseObject<M> response(M data, List<Message> messages) {
    return response(data, messages, null, null);
  }

  public static <M> ResponseObject<M> response(String errorCode,
                                               String message) {
    return response(null, null, errorCode, message);
  }

  public static <M> ResponseObject<M> response(M data, List<Message> messages, String errorCode,
                                               String message) {
    return (ResponseObject<M>) ResponseObject.builder()
        .data(data)
        .messages(messages)
        .reqId(MDC.get(Constants.REQUEST_ID_MDC_KEY))
        .errorCode(errorCode)
        .message(message)
        .build();
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
