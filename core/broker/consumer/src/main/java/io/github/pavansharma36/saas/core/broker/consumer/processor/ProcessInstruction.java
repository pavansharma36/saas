package io.github.pavansharma36.saas.core.broker.consumer.processor;

import io.github.pavansharma36.core.common.mutex.bean.CompositeLockInfo;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageSerializablePayload;
import io.github.pavansharma36.saas.core.broker.common.dao.model.MessageInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProcessInstruction {

  private final Instruction instruction;

  private final MessageInfo messageInfo;

  private final CompositeLockInfo lockInfo;

  private final MessageSerializablePayload payload;

  public enum Instruction {
    SKIP, DELAY, PROCESS
  }
}
