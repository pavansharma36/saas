package io.github.pavansharma36.saas.galaxy.common.dao.mybatis.dao;

import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.ConfigDynamicSqlSupport.classifier;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.ConfigDynamicSqlSupport.classifierValue;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import io.github.pavansharma36.core.common.id.utils.IdGeneratorUtils;
import io.github.pavansharma36.saas.core.dao.mybatis.dao.AbstractDao;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.mapper.ConfigMapper;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model.Config;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ConfigDao extends AbstractDao<Config, ConfigMapper> {

  protected ConfigDao(ConfigMapper mapper) {
    super(Config.class, IdGeneratorUtils.random32(), mapper);
  }

  public List<Config> selectByClassifier(String cl, String clValue) {
    return select(s -> s.where(classifier, isEqualTo(cl))
        .and(classifierValue, isEqualTo(clValue)));
  }

}
