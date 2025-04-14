package io.github.pavansharma36.saas.core.broker.common;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
@Getter
public final class Message {
  private final MessageType messageType;
  private final MessageDto messageDto;
}
