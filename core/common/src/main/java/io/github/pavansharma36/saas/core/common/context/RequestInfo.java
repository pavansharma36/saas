package io.github.pavansharma36.saas.core.common.context;

import java.util.Set;
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
  private Set<String> roles;

  /**
   * auth filter doesnt have response where we can update jwt.
   * keep it here and set in response if not null.
   */
  private transient String responseJwt;

  public boolean isRetry() {
    return retryAttempt > 0;
  }
}
