package io.github.pavansharma36.saas.utils.poll;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AlwaysLogEmitter implements DelayedLogEmitter {

  private final Logger logger;

  public static AlwaysLogEmitter getInstance(Logger logger) {
    return new AlwaysLogEmitter(logger);
  }

  @Override
  public void info(String message) {
    logger.info(message);
  }

  @Override
  public void info(String message, Object... args) {
    logger.info(message, args);
  }
}
