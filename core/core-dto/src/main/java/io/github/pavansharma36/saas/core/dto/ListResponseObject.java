package io.github.pavansharma36.saas.core.dto;

import io.github.pavansharma36.saas.utils.Constants;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.MDC;

@Getter
@Builder
public class ListResponseObject<T> {

  private final boolean success;
  private final String reqId = MDC.get(Constants.REQUEST_ID_MDC_KEY);
  private final Long totalCount;
  private final List<T> data;
  private final List<Message> messages;

  public static <M> ListResponseObject<M> success(List<M> data) {
    return success(data, null, null);
  }

  public static <M> ListResponseObject<M> success(List<M> data, long totalCount) {
    return success(data, null, totalCount);
  }

  public static <M> ListResponseObject<M> success(List<M> data, List<Message> messages,
                                                  Long totalCount) {
    return (ListResponseObject<M>) ListResponseObject.builder().success(true)
        .data((List<Object>) data)
        .messages(messages)
        .totalCount(totalCount)
        .build();
  }

}
