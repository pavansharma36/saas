package io.github.pavansharma36.saas.galaxy.dto.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfigValueDto {

  private String key;
  private String value;

}
