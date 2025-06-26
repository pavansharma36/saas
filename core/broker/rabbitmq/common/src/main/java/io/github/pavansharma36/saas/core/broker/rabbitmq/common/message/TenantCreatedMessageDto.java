package io.github.pavansharma36.saas.core.broker.rabbitmq.common.message;

import io.github.pavansharma36.saas.core.broker.common.bean.MessageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class TenantCreatedMessageDto extends MessageDto {
  private String id;
}
