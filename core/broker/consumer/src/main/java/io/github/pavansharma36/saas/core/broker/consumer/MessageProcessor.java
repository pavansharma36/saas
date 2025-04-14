package io.github.pavansharma36.saas.core.broker.consumer;

import io.github.pavansharma36.saas.core.broker.common.MessageDto;
import io.github.pavansharma36.saas.core.broker.common.MessageType;

public interface MessageProcessor<T extends MessageDto> {
  MessageType messageType();

  void process(T messageDto);
}
