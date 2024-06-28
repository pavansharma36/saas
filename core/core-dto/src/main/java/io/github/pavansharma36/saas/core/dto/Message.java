package io.github.pavansharma36.saas.core.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class Message {

  private final Severity severity;
  private final String summary;
  private final String detail;
  private final String field;

  public Message(Severity severity, String summary, String detail) {
    this(severity,summary,detail, null);
  }

  public Message(Severity severity, String summary, String detail, String field) {
    this.severity = severity;
    this.summary = summary;
    this.detail = detail;
    this.field = field;
  }

  public enum Severity {
    INFO, ERROR, WARN, SUCCESS
  }

}
