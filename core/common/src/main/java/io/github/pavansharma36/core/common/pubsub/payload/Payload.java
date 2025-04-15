package io.github.pavansharma36.core.common.pubsub.payload;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
@ToString
public class Payload implements Serializable {
  protected String eventType;
  protected Serializable data;
}
