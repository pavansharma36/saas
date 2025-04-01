package io.github.pavansharma36.saas.galaxy.client;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.core.client.feign.B2BClientBuilder;
import io.github.pavansharma36.saas.galaxy.api.ConfigApi;
import io.github.pavansharma36.saas.galaxy.api.TenantApi;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class GalaxyClientFactory {

  private static final String GALAXY_URL = Config.get("galaxy.url");

  public static ConfigApi configApi() {
    return B2BClientBuilder.build(ConfigApi.class, GALAXY_URL + "/api");
  }

  public static TenantApi tenantApi() {
    return B2BClientBuilder.build(TenantApi.class, GALAXY_URL + "/api");
  }

}
