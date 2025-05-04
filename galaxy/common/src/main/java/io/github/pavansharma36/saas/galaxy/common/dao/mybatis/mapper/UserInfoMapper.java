package io.github.pavansharma36.saas.galaxy.common.dao.mybatis.mapper;

import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.UserInfoDynamicSqlSupport.createdAt;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.UserInfoDynamicSqlSupport.createdBy;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.UserInfoDynamicSqlSupport.firstName;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.UserInfoDynamicSqlSupport.id;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.UserInfoDynamicSqlSupport.lastName;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.UserInfoDynamicSqlSupport.tenantId;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.UserInfoDynamicSqlSupport.updatedAt;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.UserInfoDynamicSqlSupport.updatedBy;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.UserInfoDynamicSqlSupport.userInfo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import io.github.pavansharma36.saas.core.dao.mybatis.mapper.BaseMapper;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model.UserInfo;
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
public interface UserInfoMapper extends BaseMapper<UserInfo>, CommonCountMapper, CommonDeleteMapper,
    CommonInsertMapper<UserInfo>, CommonUpdateMapper {
  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  BasicColumn[] selectList =
      BasicColumn.columnList(id, tenantId, firstName, lastName, createdBy, createdAt, updatedBy,
          updatedAt);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  static UpdateDSL<UpdateModel> updateAllColumns(UserInfo row, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalTo(row::getId)
        .set(tenantId).equalTo(row::getTenantId)
        .set(firstName).equalTo(row::getFirstName)
        .set(lastName).equalTo(row::getLastName)
        .set(createdBy).equalTo(row::getCreatedBy)
        .set(createdAt).equalTo(row::getCreatedAt)
        .set(updatedBy).equalTo(row::getUpdatedBy)
        .set(updatedAt).equalTo(row::getUpdatedAt);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  static UpdateDSL<UpdateModel> updateSelectiveColumns(UserInfo row, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalToWhenPresent(row::getId)
        .set(tenantId).equalToWhenPresent(row::getTenantId)
        .set(firstName).equalToWhenPresent(row::getFirstName)
        .set(lastName).equalToWhenPresent(row::getLastName)
        .set(createdBy).equalToWhenPresent(row::getCreatedBy)
        .set(createdAt).equalToWhenPresent(row::getCreatedAt)
        .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
        .set(updatedAt).equalToWhenPresent(row::getUpdatedAt);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "UserInfoResult", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
      @Result(column = "tenant_id", property = "tenantId", jdbcType = JdbcType.VARCHAR),
      @Result(column = "first_name", property = "firstName", jdbcType = JdbcType.VARCHAR),
      @Result(column = "last_name", property = "lastName", jdbcType = JdbcType.VARCHAR),
      @Result(column = "created_by", property = "createdBy", jdbcType = JdbcType.VARCHAR),
      @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "updated_by", property = "updatedBy", jdbcType = JdbcType.VARCHAR),
      @Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP)
  })
  List<UserInfo> selectMany(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @ResultMap("UserInfoResult")
  Optional<UserInfo> selectOne(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default long count(CountDSLCompleter completer) {
    return MyBatis3Utils.countFrom(this::count, userInfo, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int delete(DeleteDSLCompleter completer) {
    return MyBatis3Utils.deleteFrom(this::delete, userInfo, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int deleteByPrimaryKey(String id_) {
    return delete(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insert(UserInfo row) {
    return MyBatis3Utils.insert(this::insert, row, userInfo, c ->
        c.map(id).toProperty("id")
            .map(tenantId).toProperty("tenantId")
            .map(firstName).toProperty("firstName")
            .map(lastName).toProperty("lastName")
            .map(createdBy).toProperty("createdBy")
            .map(createdAt).toProperty("createdAt")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedAt).toProperty("updatedAt")
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insertMultiple(Collection<UserInfo> records) {
    return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userInfo, c ->
        c.map(id).toProperty("id")
            .map(tenantId).toProperty("tenantId")
            .map(firstName).toProperty("firstName")
            .map(lastName).toProperty("lastName")
            .map(createdBy).toProperty("createdBy")
            .map(createdAt).toProperty("createdAt")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedAt).toProperty("updatedAt")
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insertSelective(UserInfo row) {
    return MyBatis3Utils.insert(this::insert, row, userInfo, c ->
        c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(tenantId).toPropertyWhenPresent("tenantId", row::getTenantId)
            .map(firstName).toPropertyWhenPresent("firstName", row::getFirstName)
            .map(lastName).toPropertyWhenPresent("lastName", row::getLastName)
            .map(createdBy).toPropertyWhenPresent("createdBy", row::getCreatedBy)
            .map(createdAt).toPropertyWhenPresent("createdAt", row::getCreatedAt)
            .map(updatedBy).toPropertyWhenPresent("updatedBy", row::getUpdatedBy)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", row::getUpdatedAt)
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default Optional<UserInfo> selectOne(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectOne(this::selectOne, selectList, userInfo, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default List<UserInfo> select(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectList(this::selectMany, selectList, userInfo, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default List<UserInfo> selectDistinct(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userInfo, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default Optional<UserInfo> selectByPrimaryKey(String id_) {
    return selectOne(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int update(UpdateDSLCompleter completer) {
    return MyBatis3Utils.update(this::update, userInfo, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int updateByPrimaryKey(UserInfo row) {
    return update(c ->
        c.set(tenantId).equalTo(row::getTenantId)
            .set(firstName).equalTo(row::getFirstName)
            .set(lastName).equalTo(row::getLastName)
            .set(createdBy).equalTo(row::getCreatedBy)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedBy).equalTo(row::getUpdatedBy)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .where(id, isEqualTo(row::getId))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int updateByPrimaryKeySelective(UserInfo row) {
    return update(c ->
        c.set(tenantId).equalToWhenPresent(row::getTenantId)
            .set(firstName).equalToWhenPresent(row::getFirstName)
            .set(lastName).equalToWhenPresent(row::getLastName)
            .set(createdBy).equalToWhenPresent(row::getCreatedBy)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .where(id, isEqualTo(row::getId))
    );
  }
}