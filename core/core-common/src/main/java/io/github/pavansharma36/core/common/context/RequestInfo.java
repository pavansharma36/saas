package io.github.pavansharma36.core.common.context;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestInfo {
  private String ip;
  private String appName;
  private String appType;
}
