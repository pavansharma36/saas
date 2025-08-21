package io.github.pavansharma36.saas.galaxy.common.dao.mybatis.dao;

import io.github.pavansharma36.saas.core.common.id.IdGenerator;
import io.github.pavansharma36.saas.core.common.id.utils.IdGeneratorUtils;
import io.github.pavansharma36.saas.core.dao.common.dao.Dao;
import io.github.pavansharma36.saas.core.dao.mybatis.dao.AbstractMyBatisDao;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.mapper.UserInfoMapper;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDao extends AbstractMyBatisDao<UserInfo, UserInfoMapper>
    implements Dao<UserInfo> {

  protected UserInfoDao(UserInfoMapper mapper) {
    super(UserInfo.class, IdGeneratorUtils.tenant32(), mapper);
  }

  public IdGenerator getIdGenerator() {
    return IdGeneratorUtils.tenant32();
  }

}
