package io.github.pavansharma36.saas.core.client;

import feign.Request;
import io.github.pavansharma36.core.common.config.Config;
import java.util.Set;

public abstract class AbstractBaseRetry {
  protected static final Set<Request.HttpMethod> NON_RETRYABLE_METHODS =
      Set.of(Request.HttpMethod.POST);
  protected static final Set<Integer> CLIENT_ERROR_RETRY_STATUSES = Set.of(408, 425, 429);

  protected long getRetryDelay(int i) {
    final int retryDelayMillis = Config.getInt("http.retry.backoff.millis", 250);
    final boolean retryExponential = Config.getBoolean("http.retry.backoff.exponential", true);
    return retryExponential ? ((long) Math.pow(2, i) * retryDelayMillis) : retryDelayMillis;
  }
}
