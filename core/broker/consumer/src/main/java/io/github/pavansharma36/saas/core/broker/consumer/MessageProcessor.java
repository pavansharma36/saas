package io.github.pavansharma36.saas.core.broker.consumer;

import io.github.pavansharma36.saas.core.broker.common.api.MessageType;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageDto;

public interface MessageProcessor<T extends MessageDto> {
  MessageType messageType();

  void process(T messageDto);
}
