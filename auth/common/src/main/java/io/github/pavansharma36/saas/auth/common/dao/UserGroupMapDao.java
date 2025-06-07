package io.github.pavansharma36.saas.auth.common.dao;

import io.github.pavansharma36.core.common.id.utils.IdGeneratorUtils;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.mapper.UserGroupMapMapper;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.model.UserGroupMap;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupMapDynamicSqlSupport;
import io.github.pavansharma36.saas.core.dao.mybatis.dao.AbstractMyBatisDao;
import java.util.List;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class UserGroupMapDao extends AbstractMyBatisDao<UserGroupMap, UserGroupMapMapper> {

  public UserGroupMapDao(UserGroupMapMapper mapper) {
    super(UserGroupMap.class, IdGeneratorUtils.tenant32(), mapper);
  }

  public List<UserGroupMap> findByUserId(String userId) {
    return select(q -> q.where(UserGroupMapDynamicSqlSupport.userId, SqlBuilder.isEqualTo(userId)));
  }

}
