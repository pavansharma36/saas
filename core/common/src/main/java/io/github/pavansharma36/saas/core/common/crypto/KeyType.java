package io.github.pavansharma36.saas.core.common.crypto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KeyType {
  DEFAULT("default"),
  DB("db"),
  JWT("jwt");

  private final String prefix;
}
