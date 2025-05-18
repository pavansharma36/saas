package io.github.pavansharma36.saas.core.broker.common;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public final class Message {
  private final MessageType messageType;
  private final MessageDto messageDto;
  private final MessagePriority priority;
}
