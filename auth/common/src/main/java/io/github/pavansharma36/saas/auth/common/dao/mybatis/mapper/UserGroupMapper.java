package io.github.pavansharma36.saas.auth.common.dao.mybatis.mapper;

import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupDynamicSqlSupport.createdAt;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupDynamicSqlSupport.createdBy;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupDynamicSqlSupport.description;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupDynamicSqlSupport.id;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupDynamicSqlSupport.name;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupDynamicSqlSupport.tenantId;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupDynamicSqlSupport.updatedAt;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupDynamicSqlSupport.updatedBy;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupDynamicSqlSupport.userGroup;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import io.github.pavansharma36.saas.auth.common.dao.mybatis.model.UserGroup;
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
public interface UserGroupMapper
    extends BaseMapper<UserGroup>, CommonCountMapper, CommonDeleteMapper,
    CommonInsertMapper<UserGroup>, CommonUpdateMapper {
  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  BasicColumn[] selectList =
      BasicColumn.columnList(id, tenantId, name, description, createdBy, createdAt, updatedBy,
          updatedAt);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  static UpdateDSL<UpdateModel> updateAllColumns(UserGroup row, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalTo(row::getId)
        .set(tenantId).equalTo(row::getTenantId)
        .set(name).equalTo(row::getName)
        .set(description).equalTo(row::getDescription)
        .set(createdBy).equalTo(row::getCreatedBy)
        .set(createdAt).equalTo(row::getCreatedAt)
        .set(updatedBy).equalTo(row::getUpdatedBy)
        .set(updatedAt).equalTo(row::getUpdatedAt);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  static UpdateDSL<UpdateModel> updateSelectiveColumns(UserGroup row, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalToWhenPresent(row::getId)
        .set(tenantId).equalToWhenPresent(row::getTenantId)
        .set(name).equalToWhenPresent(row::getName)
        .set(description).equalToWhenPresent(row::getDescription)
        .set(createdBy).equalToWhenPresent(row::getCreatedBy)
        .set(createdAt).equalToWhenPresent(row::getCreatedAt)
        .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
        .set(updatedAt).equalToWhenPresent(row::getUpdatedAt);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "UserGroupResult", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
      @Result(column = "tenant_id", property = "tenantId", jdbcType = JdbcType.VARCHAR),
      @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
      @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
      @Result(column = "created_by", property = "createdBy", jdbcType = JdbcType.VARCHAR),
      @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "updated_by", property = "updatedBy", jdbcType = JdbcType.VARCHAR),
      @Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP)
  })
  List<UserGroup> selectMany(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @ResultMap("UserGroupResult")
  Optional<UserGroup> selectOne(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default long count(CountDSLCompleter completer) {
    return MyBatis3Utils.countFrom(this::count, userGroup, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int delete(DeleteDSLCompleter completer) {
    return MyBatis3Utils.deleteFrom(this::delete, userGroup, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int deleteByPrimaryKey(String id_) {
    return delete(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insert(UserGroup row) {
    return MyBatis3Utils.insert(this::insert, row, userGroup, c ->
        c.map(id).toProperty("id")
            .map(tenantId).toProperty("tenantId")
            .map(name).toProperty("name")
            .map(description).toProperty("description")
            .map(createdBy).toProperty("createdBy")
            .map(createdAt).toProperty("createdAt")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedAt).toProperty("updatedAt")
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insertMultiple(Collection<UserGroup> records) {
    return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userGroup, c ->
        c.map(id).toProperty("id")
            .map(tenantId).toProperty("tenantId")
            .map(name).toProperty("name")
            .map(description).toProperty("description")
            .map(createdBy).toProperty("createdBy")
            .map(createdAt).toProperty("createdAt")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedAt).toProperty("updatedAt")
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insertSelective(UserGroup row) {
    return MyBatis3Utils.insert(this::insert, row, userGroup, c ->
        c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(tenantId).toPropertyWhenPresent("tenantId", row::getTenantId)
            .map(name).toPropertyWhenPresent("name", row::getName)
            .map(description).toPropertyWhenPresent("description", row::getDescription)
            .map(createdBy).toPropertyWhenPresent("createdBy", row::getCreatedBy)
            .map(createdAt).toPropertyWhenPresent("createdAt", row::getCreatedAt)
            .map(updatedBy).toPropertyWhenPresent("updatedBy", row::getUpdatedBy)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", row::getUpdatedAt)
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default Optional<UserGroup> selectOne(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectOne(this::selectOne, selectList, userGroup, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default List<UserGroup> select(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectList(this::selectMany, selectList, userGroup, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default List<UserGroup> selectDistinct(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userGroup, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default Optional<UserGroup> selectByPrimaryKey(String id_) {
    return selectOne(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int update(UpdateDSLCompleter completer) {
    return MyBatis3Utils.update(this::update, userGroup, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int updateByPrimaryKey(UserGroup row) {
    return update(c ->
        c.set(tenantId).equalTo(row::getTenantId)
            .set(name).equalTo(row::getName)
            .set(description).equalTo(row::getDescription)
            .set(createdBy).equalTo(row::getCreatedBy)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedBy).equalTo(row::getUpdatedBy)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .where(id, isEqualTo(row::getId))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int updateByPrimaryKeySelective(UserGroup row) {
    return update(c ->
        c.set(tenantId).equalToWhenPresent(row::getTenantId)
            .set(name).equalToWhenPresent(row::getName)
            .set(description).equalToWhenPresent(row::getDescription)
            .set(createdBy).equalToWhenPresent(row::getCreatedBy)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .where(id, isEqualTo(row::getId))
    );
  }
}