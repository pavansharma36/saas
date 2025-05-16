package io.github.pavansharma36.saas.core.client.feign.client;

import io.github.pavansharma36.saas.core.api.CoreApi;
import io.github.pavansharma36.saas.core.client.feign.B2BClientBuilder;

public class CoreApiClientFactory {

  public static CoreApi client(String url) {
    return B2BClientBuilder.build(CoreApi.class, url);
  }

}
