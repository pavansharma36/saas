package io.github.pavansharma36.saas.utils.poll;

public interface DelayedLogEmitter {
  void info(String message);

  void info(String message, Object... args);
}
