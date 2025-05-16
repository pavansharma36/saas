package io.github.pavansharma36.saas.core.client.http;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.core.client.AbstractBaseRetry;
import java.io.IOException;
import java.time.Instant;
import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.client5.http.utils.DateUtils;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.util.Args;
import org.apache.hc.core5.util.TimeValue;

public class AppHttpRequestRetryStrategy extends AbstractBaseRetry
    implements HttpRequestRetryStrategy {

  public AppHttpRequestRetryStrategy() {
    super();
  }

  @Override
  public boolean retryRequest(HttpRequest httpRequest, IOException e, int i,
                              HttpContext httpContext) {
    if (!retryRequest(i)) {
      return false;
    }
    return NON_RETRYABLE_METHODS.stream().map(Enum::name)
        .noneMatch(m -> m.equals(httpRequest.getMethod()));
  }

  @Override
  public boolean retryRequest(HttpResponse httpResponse, int i, HttpContext httpContext) {
    return retryRequest(i) && (httpResponse.getCode() >= 500 ||
        CLIENT_ERROR_RETRY_STATUSES.contains(httpResponse.getCode()));
  }

  private TimeValue getRetryInterval(int i) {
    return TimeValue.ofMilliseconds(getRetryDelay(i));
  }

  @Override
  public TimeValue getRetryInterval(HttpRequest request, IOException exception, int execCount,
                                    HttpContext context) {
    return getRetryInterval(execCount);
  }

  @Override
  public TimeValue getRetryInterval(HttpResponse response, int i, HttpContext httpContext) {
    Args.notNull(response, "response");
    Header header = response.getFirstHeader("Retry-After");
    TimeValue retryAfter = null;
    if (header != null) {
      String value = header.getValue();

      try {
        retryAfter = TimeValue.ofSeconds(Long.parseLong(value));
      } catch (NumberFormatException var9) {
        Instant retryAfterDate = DateUtils.parseStandardDate(value);
        if (retryAfterDate != null) {
          retryAfter =
              TimeValue.ofMilliseconds(retryAfterDate.toEpochMilli() - System.currentTimeMillis());
        }
      }

      if (TimeValue.isPositive(retryAfter)) {
        return retryAfter;
      }
    }

    return getRetryInterval(i);
  }

  private boolean retryRequest(int execCount) {
    final int maxRetry = Config.getInt("http.retry.max.count", 5);
    return execCount < maxRetry;
  }
}
