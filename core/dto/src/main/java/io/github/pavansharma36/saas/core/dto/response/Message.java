package io.github.pavansharma36.saas.core.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class Message {

  private final Severity severity;
  private final String summary;
  private final String detail;
  private final String field;

  public enum Severity {
    INFO, ERROR, WARN, SUCCESS
  }

}
