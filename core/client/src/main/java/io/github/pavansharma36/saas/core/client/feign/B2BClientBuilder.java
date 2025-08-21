package io.github.pavansharma36.saas.core.client.feign;

import feign.Feign;
import feign.Logger;
import feign.hc5.ApacheHttp5Client;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.spring.SpringContract;
import io.github.pavansharma36.saas.core.common.config.Config;
import io.github.pavansharma36.saas.core.client.feign.interceptor.B2BRequestInterceptor;
import io.github.pavansharma36.saas.core.client.feign.logging.AppSlf4jLogger;
import io.github.pavansharma36.saas.core.client.feign.retry.Custom5xxErrorDecoder;
import io.github.pavansharma36.saas.core.client.feign.retry.CustomRetryer;
import io.github.pavansharma36.saas.core.client.http.HttpClientFactory;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class B2BClientBuilder {

  public static <T> T build(Class<T> clazz, String target) {
    return Feign.builder()
        .client(new ApacheHttp5Client(HttpClientFactory.nonRetryingHttpClient()))
        .contract(new SpringContract())
        .decoder(new JacksonDecoder(JsonUtils.mapper()))
        .encoder(new JacksonEncoder(JsonUtils.mapper()))
        .errorDecoder(new Custom5xxErrorDecoder())
        .requestInterceptor(new B2BRequestInterceptor())
        .retryer(new CustomRetryer())
        .logger(new AppSlf4jLogger())
        .logLevel(Logger.Level.valueOf(Config.get("feign.log.level", Logger.Level.BASIC.name())))
        .target(clazz, target);
  }

}
