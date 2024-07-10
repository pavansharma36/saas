package io.github.pavansharma36.saas.galaxy.client;

import io.github.pavansharma36.saas.galaxy.api.ConfigApi;
import io.github.pavansharma36.saas.galaxy.api.TenantApi;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class GalaxyClientFactory {

  public static ConfigApi configApi() {
    return null;
  }

  public static TenantApi tenantApi() {
    return null;
  }

}
