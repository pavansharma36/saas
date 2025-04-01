package io.github.pavansharma36.saas.galaxy.dto.config;

import io.github.pavansharma36.saas.core.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfigValueDto extends BaseDto {

  private String key;
  private String value;

}
