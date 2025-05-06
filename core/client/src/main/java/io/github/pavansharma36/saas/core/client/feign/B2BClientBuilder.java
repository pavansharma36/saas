package io.github.pavansharma36.saas.core.client.feign;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.spring.SpringContract;
import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class B2BClientBuilder {

  public static <T> T build(Class<T> clazz, String target) {
    return Feign.builder()
        .decoder(new JacksonDecoder(JsonUtils.mapper()))
        .encoder(new JacksonEncoder(JsonUtils.mapper()))
        .errorDecoder(new Custom5xxErrorDecoder())
        .contract(new SpringContract())
        .requestInterceptor(new B2BRequestInterceptor())
        .retryer(new CustomRetryer())
        .logger(new AppSlf4jLogger())
        .logLevel(Logger.Level.valueOf(Config.get("feign.log.level", Logger.Level.BASIC.name())))
        .target(clazz, target);
  }

}
