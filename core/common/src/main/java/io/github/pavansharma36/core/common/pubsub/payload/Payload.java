package io.github.pavansharma36.core.common.pubsub.payload;

import io.github.pavansharma36.saas.utils.Utils;
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
  private final String payloadId = Utils.randomRequestId();
  private String eventType;
  private Serializable data;
}
