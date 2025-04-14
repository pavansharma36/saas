package io.github.pavansharma36.core.pubsub.common.payload;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class Payload {
  private String eventType;
  private Map<String, Object> data;
}
