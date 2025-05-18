package io.github.pavansharma36.saas.core.broker.consumer;

import io.github.pavansharma36.saas.core.broker.common.MessageDto;
import io.github.pavansharma36.saas.core.broker.common.MessagePriority;
import io.github.pavansharma36.saas.core.broker.common.MessageType;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public class MessageReceivable {
  private final String messageId;
  private final MessageType messageType;
  private final MessagePriority messagePriority;
  private final MessageDto messageDto;
}
