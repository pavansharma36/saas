package io.github.pavansharma36.saas.galaxy.client;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.spring.SpringContract;
import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.core.client.feign.B2BRequestInterceptor;
import io.github.pavansharma36.saas.galaxy.api.ConfigApi;
import io.github.pavansharma36.saas.galaxy.api.TenantApi;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class GalaxyClientFactory {

  private static final String GALAXY_URL = Config.get("galaxy.url");

  public static ConfigApi configApi() {
    return Feign.builder()
        .decoder(new JacksonDecoder(JsonUtils.mapper()))
        .encoder(new JacksonEncoder(JsonUtils.mapper()))
        .contract(new SpringContract())
        .requestInterceptor(new B2BRequestInterceptor())
        .target(ConfigApi.class, GALAXY_URL + "/api");
  }

  public static TenantApi tenantApi() {
    return null;
  }

}
