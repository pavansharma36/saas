package io.github.pavansharma36.saas.auth.common.dao.mybatis.mapper;

import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserAccountDynamicSqlSupport.accountNonExpired;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserAccountDynamicSqlSupport.accountNonLocked;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserAccountDynamicSqlSupport.attemptId;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserAccountDynamicSqlSupport.createdAt;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserAccountDynamicSqlSupport.createdBy;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserAccountDynamicSqlSupport.credentialsNonExpired;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserAccountDynamicSqlSupport.id;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserAccountDynamicSqlSupport.password;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserAccountDynamicSqlSupport.updatedAt;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserAccountDynamicSqlSupport.updatedBy;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserAccountDynamicSqlSupport.userAccount;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserAccountDynamicSqlSupport.username;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import io.github.pavansharma36.saas.auth.common.dao.mybatis.model.UserAccount;
import io.github.pavansharma36.saas.core.dao.mybatis.mapper.BaseMapper;
import jakarta.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface UserAccountMapper
    extends BaseMapper<UserAccount>, CommonCountMapper, CommonDeleteMapper,
    CommonInsertMapper<UserAccount>, CommonUpdateMapper {
  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  BasicColumn[] selectList =
      BasicColumn.columnList(id, username, password, accountNonExpired, credentialsNonExpired,
          accountNonLocked, createdBy, createdAt, updatedBy, updatedAt, attemptId);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  static UpdateDSL<UpdateModel> updateAllColumns(UserAccount row, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalTo(row::getId)
        .set(username).equalTo(row::getUsername)
        .set(password).equalTo(row::getPassword)
        .set(accountNonExpired).equalTo(row::isAccountNonExpired)
        .set(credentialsNonExpired).equalTo(row::isCredentialsNonExpired)
        .set(accountNonLocked).equalTo(row::isAccountNonLocked)
        .set(createdBy).equalTo(row::getCreatedBy)
        .set(createdAt).equalTo(row::getCreatedAt)
        .set(updatedBy).equalTo(row::getUpdatedBy)
        .set(updatedAt).equalTo(row::getUpdatedAt)
        .set(attemptId).equalTo(row::getAttemptId);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  static UpdateDSL<UpdateModel> updateSelectiveColumns(UserAccount row,
                                                       UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalToWhenPresent(row::getId)
        .set(username).equalToWhenPresent(row::getUsername)
        .set(password).equalToWhenPresent(row::getPassword)
        .set(accountNonExpired).equalToWhenPresent(row::isAccountNonExpired)
        .set(credentialsNonExpired).equalToWhenPresent(row::isCredentialsNonExpired)
        .set(accountNonLocked).equalToWhenPresent(row::isAccountNonLocked)
        .set(createdBy).equalToWhenPresent(row::getCreatedBy)
        .set(createdAt).equalToWhenPresent(row::getCreatedAt)
        .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
        .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
        .set(attemptId).equalToWhenPresent(row::getAttemptId);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "UserAccountResult", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
      @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
      @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
      @Result(column = "account_non_expired", property = "accountNonExpired", jdbcType = JdbcType.BIT),
      @Result(column = "credentials_non_expired", property = "credentialsNonExpired", jdbcType = JdbcType.BIT),
      @Result(column = "account_non_locked", property = "accountNonLocked", jdbcType = JdbcType.BIT),
      @Result(column = "created_by", property = "createdBy", jdbcType = JdbcType.VARCHAR),
      @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "updated_by", property = "updatedBy", jdbcType = JdbcType.VARCHAR),
      @Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "attempt_id", property = "attemptId", jdbcType = JdbcType.VARCHAR)
  })
  List<UserAccount> selectMany(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @ResultMap("UserAccountResult")
  Optional<UserAccount> selectOne(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default long count(CountDSLCompleter completer) {
    return MyBatis3Utils.countFrom(this::count, userAccount, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int delete(DeleteDSLCompleter completer) {
    return MyBatis3Utils.deleteFrom(this::delete, userAccount, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int deleteByPrimaryKey(String id_) {
    return delete(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insert(UserAccount row) {
    return MyBatis3Utils.insert(this::insert, row, userAccount, c ->
        c.map(id).toProperty("id")
            .map(username).toProperty("username")
            .map(password).toProperty("password")
            .map(accountNonExpired).toProperty("accountNonExpired")
            .map(credentialsNonExpired).toProperty("credentialsNonExpired")
            .map(accountNonLocked).toProperty("accountNonLocked")
            .map(createdBy).toProperty("createdBy")
            .map(createdAt).toProperty("createdAt")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedAt).toProperty("updatedAt")
            .map(attemptId).toProperty("attemptId")
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insertMultiple(Collection<UserAccount> records) {
    return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userAccount, c ->
        c.map(id).toProperty("id")
            .map(username).toProperty("username")
            .map(password).toProperty("password")
            .map(accountNonExpired).toProperty("accountNonExpired")
            .map(credentialsNonExpired).toProperty("credentialsNonExpired")
            .map(accountNonLocked).toProperty("accountNonLocked")
            .map(createdBy).toProperty("createdBy")
            .map(createdAt).toProperty("createdAt")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedAt).toProperty("updatedAt")
            .map(attemptId).toProperty("attemptId")
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insertSelective(UserAccount row) {
    return MyBatis3Utils.insert(this::insert, row, userAccount, c ->
        c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(username).toPropertyWhenPresent("username", row::getUsername)
            .map(password).toPropertyWhenPresent("password", row::getPassword)
            .map(accountNonExpired)
            .toPropertyWhenPresent("accountNonExpired", row::isAccountNonExpired)
            .map(credentialsNonExpired)
            .toPropertyWhenPresent("credentialsNonExpired", row::isCredentialsNonExpired)
            .map(accountNonLocked)
            .toPropertyWhenPresent("accountNonLocked", row::isAccountNonLocked)
            .map(createdBy).toPropertyWhenPresent("createdBy", row::getCreatedBy)
            .map(createdAt).toPropertyWhenPresent("createdAt", row::getCreatedAt)
            .map(updatedBy).toPropertyWhenPresent("updatedBy", row::getUpdatedBy)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", row::getUpdatedAt)
            .map(attemptId).toPropertyWhenPresent("attemptId", row::getAttemptId)
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default Optional<UserAccount> selectOne(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectOne(this::selectOne, selectList, userAccount, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default List<UserAccount> select(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectList(this::selectMany, selectList, userAccount, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default List<UserAccount> selectDistinct(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userAccount, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default Optional<UserAccount> selectByPrimaryKey(String id_) {
    return selectOne(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int update(UpdateDSLCompleter completer) {
    return MyBatis3Utils.update(this::update, userAccount, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int updateByPrimaryKey(UserAccount row) {
    return update(c ->
        c.set(username).equalTo(row::getUsername)
            .set(password).equalTo(row::getPassword)
            .set(accountNonExpired).equalTo(row::isAccountNonExpired)
            .set(credentialsNonExpired).equalTo(row::isCredentialsNonExpired)
            .set(accountNonLocked).equalTo(row::isAccountNonLocked)
            .set(createdBy).equalTo(row::getCreatedBy)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedBy).equalTo(row::getUpdatedBy)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .set(attemptId).equalTo(row::getAttemptId)
            .where(id, isEqualTo(row::getId))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int updateByPrimaryKeySelective(UserAccount row) {
    return update(c ->
        c.set(username).equalToWhenPresent(row::getUsername)
            .set(password).equalToWhenPresent(row::getPassword)
            .set(accountNonExpired).equalToWhenPresent(row::isAccountNonExpired)
            .set(credentialsNonExpired).equalToWhenPresent(row::isCredentialsNonExpired)
            .set(accountNonLocked).equalToWhenPresent(row::isAccountNonLocked)
            .set(createdBy).equalToWhenPresent(row::getCreatedBy)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .set(attemptId).equalToWhenPresent(row::getAttemptId)
            .where(id, isEqualTo(row::getId))
    );
  }
}