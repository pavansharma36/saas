package io.github.pavansharma36.saas.core.broker.common.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class IdMessageDto extends MessageDto {
  private String id;
}
