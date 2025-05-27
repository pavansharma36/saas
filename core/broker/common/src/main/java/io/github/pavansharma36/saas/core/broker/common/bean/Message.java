package io.github.pavansharma36.saas.core.broker.common.bean;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.common.api.MessageType;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public final class Message implements Serializable {
  private final MessageType messageType;
  private final MessagePriority priority;
  @JsonTypeInfo(visible = true, use = JsonTypeInfo.Id.CLASS)
  private final MessageDto messageDto;

  /**
   * if true, MessageInfo will be created and stored in database.
   */
  private final boolean trackWithDatabase;

  /**
   * if trackWithDatabase is true along with lockOnProcess,
   * lock with messageId will be acquired to avoid duplicate processing.
   */
  private final boolean lockOnProcess;

  /**
   * if trackWithDatabase and idempotent is true than will be processed
   * if status is not completed and previous processing attempt was detected.
   * otherwise will be updated to failed state.
   */
  private final boolean idempotent;

  /**
   * if a message needs to be processed in order, set this key in such way its same for message type.
   */
  private final String orderKey;

}
