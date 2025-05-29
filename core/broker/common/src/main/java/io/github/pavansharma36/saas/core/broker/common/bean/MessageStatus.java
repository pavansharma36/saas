package io.github.pavansharma36.saas.core.broker.common.bean;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageStatus {
  DISPATCHED(false),
  PROCESSING(false),
  SUCCESS(true),
  FAILED(true),
  EXPIRED(true);

  private final boolean isFinal;
}
