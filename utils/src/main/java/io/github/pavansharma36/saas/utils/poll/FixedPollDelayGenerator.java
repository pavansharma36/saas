package io.github.pavansharma36.saas.utils.poll;

import io.github.pavansharma36.saas.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FixedPollDelayGenerator implements PollDelayGenerator {

  private final long sleepMillis;
  
  @Override
  public void delay(boolean hadResult) {
    Utils.sleepQuietly(sleepMillis);
  }
}
