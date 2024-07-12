package io.github.pavansharma36.saas.galaxy.common.dto.mapper;

import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model.Config;
import io.github.pavansharma36.saas.galaxy.dto.config.ConfigDTO;
import io.github.pavansharma36.saas.galaxy.dto.config.ConfigValueDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ConfigDTOMapper extends BaseMapper {

  public static ConfigDTO mapTo(Config config) {
    ConfigDTO dto = new ConfigDTO();
    BaseMapper.setCommonField(dto, config);
    dto.setClassifier(config.getClassifier());
    dto.setClassifierValue(config.getClassifierValue());
    dto.setConfigName(config.getConfigName());
    dto.setConfigValue(config.getConfigValue());
    return dto;
  }

  public static ConfigValueDTO mapToValue(Config config) {
    ConfigValueDTO dto = new ConfigValueDTO();
    dto.setKey(config.getConfigName());
    dto.setValue(config.getConfigValue());
    return dto;
  }

}
