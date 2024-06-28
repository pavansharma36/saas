package io.github.pavansharma36.saas.core.client;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.utils.Constants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class HttpClientFactory {

  private static final HttpClient b2bHttpClient;

  static {
    String b2bHeader = Config.get(Constants.B2B_SECRET_CONF);
    b2bHttpClient = HttpClientBuilder.create()
        .build();
  }


  public static HttpClient b2bHttpClient() {
    return b2bHttpClient;
  }

}
