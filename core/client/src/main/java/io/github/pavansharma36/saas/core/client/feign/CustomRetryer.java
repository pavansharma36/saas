package io.github.pavansharma36.saas.core.client.feign;

import feign.RetryableException;
import feign.Retryer;
import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.utils.Constants;
import io.github.pavansharma36.saas.utils.Utils;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomRetryer implements Retryer {

  private final int maxDelay = Config.getInt("http.retry.max.delay", 30);
  private final long endTime = System.currentTimeMillis() + Duration.ofSeconds(maxDelay).toMillis();

  private int attempt = 0;

  @Override
  public void continueOrPropagate(RetryableException e) {
    final int maxRetry = Config.getInt("http.retry.max.count", 5);

    if (++attempt > maxRetry) {
      throw e;
    }

    final long currentMillis = System.currentTimeMillis();

    if (currentMillis > endTime) {
      log.info("Already exceeded max delay of {} seconds, raising {}", maxDelay, e.getMessage());
      throw e;
    }

    Long sleepSeconds = e.retryAfter();
    if (sleepSeconds == null) {
      final int retryDelaySeconds = Config.getInt("http.retry.backoff.seconds", 1);
      final boolean retryExponential = Config.getBoolean("http.retry.backoff.exponential", true);
      sleepSeconds = retryExponential ? ((long) attempt * retryDelaySeconds) : retryDelaySeconds;
    }

    if (sleepSeconds < maxDelay &&
        (currentMillis + Duration.ofSeconds(sleepSeconds).toMillis()) < endTime) {
      log.info("RetryAttempt >>> {}: Waiting for {} seconds before retrying {}", attempt,
          sleepSeconds, e.getMessage());
      Utils.sleepSecondsQuietly(Math.toIntExact(sleepSeconds));
    } else {
      log.warn("About to exceed max delay of {} for {}", maxDelay, e.getMessage());
      Utils.sleepQuietly(endTime - currentMillis);
    }

    e.request().requestTemplate().removeHeader(Constants.Header.RETRY_ATTEMPT_HEADER);
    e.request().requestTemplate()
        .header(Constants.Header.RETRY_ATTEMPT_HEADER, String.valueOf(attempt));

  }

  @Override
  public Retryer clone() {
    return new CustomRetryer();
  }
}
