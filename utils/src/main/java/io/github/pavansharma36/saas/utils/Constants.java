package io.github.pavansharma36.saas.utils;

import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * class for all constants.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Constants {

  public static final String REQUEST_ID_MDC_KEY = "rId";
  public static final String MESSAGE_ID_MDC_KEY = "mId";
  public static final String MESSAGE_TYPE_MDC_KEY = "mType";
  public static final String ATTEMPT_ID_MDC_KEY = "attemptId";
  public static final String API_MDC_KEY = "api";
  public static final String AUTHORIZATION_TYPE_BEARER = "Bearer";

  public static final String B2B_SECRET_CONF = "b2b.secret";


  /**
   * class for all headers.
   */
  public static class Header {
    public static final String REQUEST_ID_HEADER = "X-Request-Id";
    public static final String ATTEMPT_ID_HEADER = "X-Attempt-Id";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String B2B_SECRET_HEADER = "X-b2b-Secret";
    public static final String APP_NAME_HEADER = "X-App-Name";
    public static final String APP_TYPE_HEADER = "X-App-Type";
    public static final String TENANT_ID_HEADER = "X-Tenant-Id";
    public static final String USER_ID_HEADER = "X-User-Id";
    public static final String USER_ROLES_HEADER = "X-User-Roles";
    public static final String FORWARDED_FOR_HEADER = "X-Forwarded-For";
    public static final String RETRY_AFTER_HEADER = "Retry-After";
    public static final String RETRY_ATTEMPT_HEADER = "X-Retry-Attempt";
    public static final String CONTENT_TYPE = "Content-Type";

    public static final Set<String> SENSITIVE_HEADERS =
        Set.of(B2B_SECRET_HEADER, AUTHORIZATION_HEADER);
  }

}
