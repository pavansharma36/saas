package io.github.pavansharma36.saas.core.server.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Message {

  private final Severity severity;
  private final String summary;
  private final String detail;

  public enum Severity {
    INFO, ERROR, WARN, SUCCESS
  }

}
