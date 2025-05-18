package io.github.pavansharma36.saas.core.broker.common;

import io.github.pavansharma36.saas.utils.Named;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum MessagePriority implements Named {

  HIGHEST(100, "-highest"),
  HIGH(80, "-high"),
  NORMAL(60, ""),
  LOW(40, "-low"),
  LOWEST(20, "-lowest");

  private final int priority;
  private final String queueNameSuffix;

  @Override
  public String getName() {
    return name();
  }
}
