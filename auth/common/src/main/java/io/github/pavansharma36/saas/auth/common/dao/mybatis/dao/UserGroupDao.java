package io.github.pavansharma36.saas.auth.common.dao.mybatis.dao;

import io.github.pavansharma36.saas.core.common.id.utils.IdGeneratorUtils;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.mapper.UserGroupMapper;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.model.UserGroup;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupDynamicSqlSupport;
import io.github.pavansharma36.saas.core.dao.mybatis.dao.AbstractTenantDao;
import org.mybatis.dynamic.sql.SqlColumn;
import org.springframework.stereotype.Repository;

@Repository
public class UserGroupDao extends AbstractTenantDao<UserGroup, UserGroupMapper> {

  protected UserGroupDao(UserGroupMapper mapper) {
    super(UserGroup.class, IdGeneratorUtils.tenant32(), mapper);
  }

  @Override
  protected SqlColumn<String> idColumn() {
    return UserGroupDynamicSqlSupport.id;
  }

  @Override
  protected SqlColumn<String> tenantIdColumn() {
    return UserGroupDynamicSqlSupport.tenantId;
  }
}
