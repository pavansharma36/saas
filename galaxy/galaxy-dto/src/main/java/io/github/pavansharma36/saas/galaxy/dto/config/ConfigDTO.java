package io.github.pavansharma36.saas.galaxy.dto.config;

import io.github.pavansharma36.saas.core.dto.DTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigDTO extends DTO {
  private String classifier;
  private String classifierValue;
  private String configName;
  private String configValue;
}
