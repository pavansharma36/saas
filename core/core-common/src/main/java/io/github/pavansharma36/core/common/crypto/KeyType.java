package io.github.pavansharma36.core.common.crypto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KeyType {
  DEFAULT("default"),
  DB("db");

  private final String prefix;
}
