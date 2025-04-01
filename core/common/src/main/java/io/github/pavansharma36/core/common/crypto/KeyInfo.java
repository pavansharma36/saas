package io.github.pavansharma36.core.common.crypto;

import java.security.Key;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class KeyInfo {
  private final KeyType type;
  private final String alias;
  private final Key key;
}
