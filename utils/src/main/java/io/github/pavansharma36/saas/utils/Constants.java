package io.github.pavansharma36.saas.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Constants {

  public static final String REQUEST_ID_HEADER = "x-request-id";
  public static final String REQUEST_ID_MDC_KEY = "rId";
  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String AUTHORIZATION_TYPE_BEARER = "Bearer";
  public static final String B2B_SECRET_HEADER = "x-app-secret";
  public static final String B2B_SECRET_CONF = "b2b.secret";

}
