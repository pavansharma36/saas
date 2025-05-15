package io.github.pavansharma36.saas.core.client.feign.client;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.spring.SpringContract;
import io.github.pavansharma36.saas.core.api.CoreApi;
import io.github.pavansharma36.saas.utils.json.JsonUtils;

public class CoreApiClientFactory {

  public static CoreApi client(String url) {
    return Feign.builder()
        .decoder(new JacksonDecoder(JsonUtils.mapper()))
        .encoder(new JacksonEncoder(JsonUtils.mapper()))
        .contract(new SpringContract())
        .target(CoreApi.class, url);
  }

}
