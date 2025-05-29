package io.github.pavansharma36.saas.core.broker.consumer.processor;

import io.github.pavansharma36.saas.core.broker.common.api.MessageType;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageDto;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageSerializablePayload;
import java.io.IOException;

public interface MessageProcessor {
  MessageType messageType();

  ProcessInstruction canProcess(MessageSerializablePayload payload) throws IOException;

  void process(ProcessInstruction instruction, MessageDto messageDto) throws IOException;
}
