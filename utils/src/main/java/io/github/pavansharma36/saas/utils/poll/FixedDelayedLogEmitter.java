package io.github.pavansharma36.saas.utils.poll;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;

public class FixedDelayedLogEmitter implements DelayedLogEmitter {

  private final AtomicLong lastLog = new AtomicLong(0);
  private final long logDelayMillis;
  private final Logger logger;

  public FixedDelayedLogEmitter(Duration duration, Logger logger) {
    this.logDelayMillis = duration.toMillis();
    this.logger = logger;
  }

  @Override
  public void info(String message) {
    long currentMillis = System.currentTimeMillis();
    if (currentMillis > lastLog.get() + logDelayMillis) {
      logger.info(message);
      lastLog.set(currentMillis);
    }
  }

  @Override
  public void info(String message, Object... args) {
    long currentMillis = System.currentTimeMillis();
    if (currentMillis > lastLog.get() + logDelayMillis) {
      logger.info(message, args);
      lastLog.set(currentMillis);
    }
  }
}
