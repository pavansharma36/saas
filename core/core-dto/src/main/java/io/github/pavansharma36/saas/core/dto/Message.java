package io.github.pavansharma36.saas.core.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Message {

  private final Severity severity;
  private final String summary;
  private final String detail;
  private final String field;

  public enum Severity {
    INFO, ERROR, WARN, SUCCESS
  }

}
