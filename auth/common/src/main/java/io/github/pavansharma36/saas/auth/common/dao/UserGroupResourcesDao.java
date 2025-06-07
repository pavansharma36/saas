package io.github.pavansharma36.saas.auth.common.dao;

import io.github.pavansharma36.core.common.id.utils.IdGeneratorUtils;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.mapper.UserGroupResourcesMapper;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.model.UserGroupResources;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupResourcesDynamicSqlSupport;
import io.github.pavansharma36.saas.core.dao.mybatis.dao.AbstractMyBatisDao;
import io.github.pavansharma36.saas.utils.collections.CollectionUtils;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class UserGroupResourcesDao
    extends AbstractMyBatisDao<UserGroupResources, UserGroupResourcesMapper> {

  public UserGroupResourcesDao(UserGroupResourcesMapper mapper) {
    super(UserGroupResources.class, IdGeneratorUtils.tenant32(), mapper);
  }

  public List<UserGroupResources> findByUserGroupIds(Set<String> userGroupIds) {
    if (CollectionUtils.isEmpty(userGroupIds)) {
      return Collections.emptyList();
    }
    return select(q -> q.where(UserGroupResourcesDynamicSqlSupport.userGroupId,
        SqlBuilder.isIn(userGroupIds)));
  }
}
