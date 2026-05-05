package io.github.pavansharma36.saas.galaxy.common.broker.payload;

import io.github.pavansharma36.saas.core.broker.common.bean.MessageDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class TestMessageDto extends MessageDto {

  private String testId;

}
