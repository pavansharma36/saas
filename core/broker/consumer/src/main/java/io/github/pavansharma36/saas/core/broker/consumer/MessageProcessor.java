package io.github.pavansharma36.saas.core.broker.consumer;

import io.github.pavansharma36.saas.core.broker.common.api.MessageType;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageDto;

public interface MessageProcessor {
  MessageType messageType();

  boolean canProcess(String messageId, MessageDto messageDto);

  void process(MessageDto messageDto);
}
