package io.github.pavansharma36.saas.utils.poll;

public interface PollDelayGenerator {

  default void delay() {
    delay(false);
  }

  void delay(boolean hadResult);

}
