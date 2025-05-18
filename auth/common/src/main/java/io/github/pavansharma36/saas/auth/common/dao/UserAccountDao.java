package io.github.pavansharma36.saas.auth.common.dao;

import io.github.pavansharma36.core.common.id.utils.IdGeneratorUtils;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.mapper.UserAccountMapper;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.model.UserAccount;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserAccountDynamicSqlSupport;
import io.github.pavansharma36.saas.core.dao.common.dao.Dao;
import io.github.pavansharma36.saas.core.dao.mybatis.dao.AbstractMyBatisDao;
import java.util.Optional;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountDao extends AbstractMyBatisDao<UserAccount, UserAccountMapper>
    implements Dao<UserAccount> {

  protected UserAccountDao(UserAccountMapper mapper) {
    super(UserAccount.class, IdGeneratorUtils.tenant32(), mapper);
  }

  public Optional<UserAccount> userAccount(String userName) {
    return selectOne(
        q -> q.where(UserAccountDynamicSqlSupport.username, SqlBuilder.isEqualTo(userName)));
  }

}
