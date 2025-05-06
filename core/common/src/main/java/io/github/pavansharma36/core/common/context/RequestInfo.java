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
  private String authHeader;
  private int retryAttempt;
  private String attemptId;

  private String tenantId;
  private String userId;

  public boolean isRetry() {
    return retryAttempt > 0;
  }
}
