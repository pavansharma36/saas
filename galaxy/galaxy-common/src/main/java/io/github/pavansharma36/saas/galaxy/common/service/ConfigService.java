package io.github.pavansharma36.saas.galaxy.common.service;

import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.mapper.ConfigMapper;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model.Config;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigService {

  private final ConfigMapper configMapper;

  public List<Config> getAllConfigs() {
    return configMapper.select(s -> s);
  }

}
