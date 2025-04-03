package io.github.pavansharma36.saas.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * class for all constants.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Constants {

  public static final String REQUEST_ID_MDC_KEY = "rId";
  public static final String ATTEMPT_ID_MDC_KEY = "attemptId";
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
    public static final String FORWARDED_FOR_HEADER = "X-Forwarded-For";
    public static final String RETRY_AFTER_HEADER = "Retry-After";
    public static final String RETRY_ATTEMPT_HEADER = "X-Retry-Attempt";
  }

}
