package io.github.pavansharma36.saas.galaxy.common.dao.mybatis.dao;

import io.github.pavansharma36.core.common.id.utils.IdGeneratorUtils;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.mapper.GalaxyMapper;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model.Galaxy;
import org.springframework.stereotype.Repository;

@Repository
public class GalaxyDAO extends AbstractDAO<Galaxy, GalaxyMapper> {

  protected GalaxyDAO(GalaxyMapper mapper) {
    super(Galaxy.class, IdGeneratorUtils.random32(), mapper);
  }
}
