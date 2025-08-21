package io.github.pavansharma36.saas.core.client.http;

import io.github.pavansharma36.saas.core.common.config.Config;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.pool.PoolReusePolicy;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class HttpClientFactory {

  private static final HttpClient httpClient;
  private static final HttpClient nonRetryingClient;

  static {
    PoolingHttpClientConnectionManager connManager =
        PoolingHttpClientConnectionManagerBuilder.create()
            .setConnPoolPolicy(PoolReusePolicy.FIFO)
            .setMaxConnTotal(Config.getInt("http.client.connection.pool.max", 100))
            .setMaxConnPerRoute(
                Config.getInt("http.client.connection.pool.max.per.route", 10))
            .build();

    HttpRequestRetryStrategy retryStrategy = new AppHttpRequestRetryStrategy();

    httpClient = HttpClientBuilder.create()
        .evictExpiredConnections()
        .setRetryStrategy(retryStrategy)
        .setConnectionManager(connManager)
        .build();

    nonRetryingClient = HttpClientBuilder.create()
        .evictExpiredConnections()
        .disableAutomaticRetries()
        .setConnectionManager(connManager)
        .build();
  }


  public static HttpClient httpClient() {
    return httpClient;
  }

  public static HttpClient nonRetryingHttpClient() {
    return nonRetryingClient;
  }


}
