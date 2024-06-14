package io.github.pavansharma36.saas.core.server.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValidationMessage {
  private final String field;
  private final String message;
}
