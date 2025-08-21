package io.github.pavansharma36.saas.search.client;

import io.github.pavansharma36.saas.core.common.config.Config;
import io.github.pavansharma36.saas.core.client.feign.B2BClientBuilder;
import io.github.pavansharma36.saas.search.api.GenericSearchApi;
import io.github.pavansharma36.saas.search.api.TenantApi;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class SearchClientFactory {

  private static final String SEARCH_URL = Config.get("search.url");
  private static final String SEARCH_API_URL = SEARCH_URL + "/api";

  public static GenericSearchApi genericSearchApi() {
    return B2BClientBuilder.build(GenericSearchApi.class, SEARCH_API_URL);
  }

  public static TenantApi tenantApi() {
    return B2BClientBuilder.build(TenantApi.class, SEARCH_API_URL);
  }


}
