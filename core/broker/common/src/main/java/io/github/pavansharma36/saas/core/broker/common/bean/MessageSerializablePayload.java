package io.github.pavansharma36.saas.core.broker.common.bean;

import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageSerializablePayload implements Serializable {
  private String messageId;
  private String messageType;
  private MessagePriority priority;
  private MessageDto messageDto;
  private Date dispatchedAt;
  private Date lastDispatchedAt;
  private DelayedQueue lastDelayedQueue;

  @ToString.Exclude
  private Map<String, byte[]> contextMap;
}
