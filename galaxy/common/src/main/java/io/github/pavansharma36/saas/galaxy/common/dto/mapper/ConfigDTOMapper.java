package io.github.pavansharma36.saas.galaxy.common.dto.mapper;

import io.github.pavansharma36.saas.core.dto.common.ConfigValueDto;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model.Config;
import io.github.pavansharma36.saas.galaxy.dto.config.ConfigDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ConfigDTOMapper extends BaseMapper {

  public static ConfigDto mapTo(Config config) {
    ConfigDto dto = new ConfigDto();
    BaseMapper.setCommonField(dto, config);
    dto.setClassifier(config.getClassifier());
    dto.setClassifierValue(config.getClassifierValue());
    dto.setConfigName(config.getConfigName());
    dto.setConfigValue(config.getConfigValue());
    return dto;
  }

  public static ConfigValueDto mapToValue(Config config) {
    ConfigValueDto dto = new ConfigValueDto();
    dto.setKey(config.getConfigName());
    dto.setValue(config.getConfigValue());
    return dto;
  }

}
