package io.github.pavansharma36.saas.galaxy.common.dao.mybatis.dao;

import io.github.pavansharma36.core.common.id.utils.IdGeneratorUtils;
import io.github.pavansharma36.saas.core.dao.common.dao.Dao;
import io.github.pavansharma36.saas.core.dao.mybatis.dao.AbstractMyBatisDao;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.mapper.TenantMapper;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model.Tenant;
import org.springframework.stereotype.Repository;

@Repository
public class TenantDao extends AbstractMyBatisDao<Tenant, TenantMapper> implements Dao<Tenant> {

  protected TenantDao(TenantMapper mapper) {
    super(Tenant.class, IdGeneratorUtils.random32(), mapper);
  }

}
