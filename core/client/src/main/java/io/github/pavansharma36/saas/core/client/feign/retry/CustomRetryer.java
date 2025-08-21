package io.github.pavansharma36.saas.core.client.feign.retry;

import feign.RetryableException;
import feign.Retryer;
import io.github.pavansharma36.saas.core.common.config.Config;
import io.github.pavansharma36.saas.core.client.AbstractBaseRetry;
import io.github.pavansharma36.saas.utils.Constants;
import io.github.pavansharma36.saas.utils.Utils;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomRetryer extends AbstractBaseRetry implements Retryer {

  private final long maxDelay = Config.getLong("http.retry.max.delay.millis", 30000L);
  private final long endTime = System.currentTimeMillis() + maxDelay;

  private int attempt = 0;

  @Override
  public void continueOrPropagate(RetryableException e) {
    final int maxRetry = Config.getInt("http.retry.max.count", 5);

    if (++attempt > maxRetry) {
      throw e;
    }

    final long currentMillis = System.currentTimeMillis();

    if (currentMillis > endTime) {
      log.info("Already exceeded max delay of {} millis, raising {}", maxDelay, e.getMessage());
      throw e;
    }

    Long sleepMillis = e.retryAfter() == null ? null : TimeUnit.SECONDS.toMillis(e.retryAfter());
    if (sleepMillis == null) {
      sleepMillis = getRetryDelay(attempt);
    }

    if (sleepMillis < maxDelay && (currentMillis + sleepMillis) < endTime) {
      log.info("RetryAttempt >>> {}: Waiting for {} millis before retrying {}", attempt,
          sleepMillis, e.getMessage());
      Utils.sleepQuietly(Math.toIntExact(sleepMillis));
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
