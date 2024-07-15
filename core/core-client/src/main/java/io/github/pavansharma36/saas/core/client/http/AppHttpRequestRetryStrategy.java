package io.github.pavansharma36.saas.core.client.http;

import java.io.IOException;
import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.util.TimeValue;

public class AppHttpRequestRetryStrategy implements HttpRequestRetryStrategy {
  @Override
  public boolean retryRequest(HttpRequest httpRequest, IOException e, int i,
                              HttpContext httpContext) {
    return false;
  }

  @Override
  public boolean retryRequest(HttpResponse httpResponse, int i, HttpContext httpContext) {
    return false;
  }

  @Override
  public TimeValue getRetryInterval(HttpResponse httpResponse, int i, HttpContext httpContext) {
    return null;
  }
}
