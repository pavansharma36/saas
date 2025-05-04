package io.github.pavansharma36.saas.auth.client;

import io.github.pavansharma36.auth.api.UserAccountApi;
import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.core.client.feign.B2BClientBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AuthClientFactory {

  private static final String AUTH_URL = Config.get("auth.url");
  private static final String AUTH_API_URL = AUTH_URL + "/api";

  public static UserAccountApi userAccountApi() {
    return B2BClientBuilder.build(UserAccountApi.class, AUTH_API_URL);
  }
}
