package io.github.pavansharma36.saas.galaxy.common;

import io.github.pavansharma36.saas.core.broker.common.bean.MessageDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestMessageDto extends MessageDto {

  private String testId;

}
