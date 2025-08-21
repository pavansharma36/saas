package io.github.pavansharma36.saas.galaxy.client;

import io.github.pavansharma36.saas.core.common.config.Config;
import io.github.pavansharma36.saas.core.client.feign.B2BClientBuilder;
import io.github.pavansharma36.saas.galaxy.api.ConfigApi;
import io.github.pavansharma36.saas.galaxy.api.TenantApi;
import io.github.pavansharma36.saas.galaxy.api.UserApi;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class GalaxyClientFactory {

  private static final String GALAXY_URL = Config.get("galaxy.url");
  private static final String GALAXY_API_URL = GALAXY_URL + "/api";

  public static ConfigApi configApi() {
    return B2BClientBuilder.build(ConfigApi.class, GALAXY_API_URL);
  }

  public static TenantApi tenantApi() {
    return B2BClientBuilder.build(TenantApi.class, GALAXY_API_URL);
  }

  public static UserApi userApi() {
    return B2BClientBuilder.build(UserApi.class, GALAXY_API_URL);
  }

}
