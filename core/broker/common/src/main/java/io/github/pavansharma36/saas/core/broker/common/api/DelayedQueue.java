package io.github.pavansharma36.saas.core.broker.common.api;

import io.github.pavansharma36.saas.utils.Named;
import java.time.Duration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DelayedQueue implements Named {

  ONE_MINUTE(Duration.ofMinutes(1L), "-one-minute-delayed"),
  FIFTEEN_MINUTE(Duration.ofMinutes(15L), "-fifteen-minute-delayed"),
  ;

  private final Duration delay;
  private final String queueNameSuffix;

  @Override
  public String getName() {
    return name();
  }
}
