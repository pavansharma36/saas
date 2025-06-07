package io.github.pavansharma36.saas.auth.common.dao.mybatis.mapper;

import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupResourcesDynamicSqlSupport.allowedActions;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupResourcesDynamicSqlSupport.createdAt;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupResourcesDynamicSqlSupport.createdBy;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupResourcesDynamicSqlSupport.id;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupResourcesDynamicSqlSupport.resourceName;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupResourcesDynamicSqlSupport.updatedAt;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupResourcesDynamicSqlSupport.updatedBy;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupResourcesDynamicSqlSupport.userGroupId;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupResourcesDynamicSqlSupport.userGroupResources;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import io.github.pavansharma36.saas.auth.common.dao.mybatis.model.UserGroupResources;
import io.github.pavansharma36.saas.core.dao.mybatis.mapper.BaseMapper;
import io.github.pavansharma36.saas.core.dao.mybatis.type.StringListTypeHandler;
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
public interface UserGroupResourcesMapper
    extends BaseMapper<UserGroupResources>, CommonCountMapper, CommonDeleteMapper,
    CommonInsertMapper<UserGroupResources>, CommonUpdateMapper {
  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  BasicColumn[] selectList =
      BasicColumn.columnList(id, userGroupId, resourceName, allowedActions, createdBy, createdAt,
          updatedBy, updatedAt);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  static UpdateDSL<UpdateModel> updateAllColumns(UserGroupResources row,
                                                 UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalTo(row::getId)
        .set(userGroupId).equalTo(row::getUserGroupId)
        .set(resourceName).equalTo(row::getResourceName)
        .set(allowedActions).equalTo(row::getAllowedActions)
        .set(createdBy).equalTo(row::getCreatedBy)
        .set(createdAt).equalTo(row::getCreatedAt)
        .set(updatedBy).equalTo(row::getUpdatedBy)
        .set(updatedAt).equalTo(row::getUpdatedAt);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  static UpdateDSL<UpdateModel> updateSelectiveColumns(UserGroupResources row,
                                                       UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalToWhenPresent(row::getId)
        .set(userGroupId).equalToWhenPresent(row::getUserGroupId)
        .set(resourceName).equalToWhenPresent(row::getResourceName)
        .set(allowedActions).equalToWhenPresent(row::getAllowedActions)
        .set(createdBy).equalToWhenPresent(row::getCreatedBy)
        .set(createdAt).equalToWhenPresent(row::getCreatedAt)
        .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
        .set(updatedAt).equalToWhenPresent(row::getUpdatedAt);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "UserGroupResourcesResult", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
      @Result(column = "user_group_id", property = "userGroupId", jdbcType = JdbcType.VARCHAR),
      @Result(column = "resource_name", property = "resourceName", jdbcType = JdbcType.VARCHAR),
      @Result(column = "allowed_actions", property = "allowedActions", typeHandler = StringListTypeHandler.class, jdbcType = JdbcType.ARRAY),
      @Result(column = "created_by", property = "createdBy", jdbcType = JdbcType.VARCHAR),
      @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "updated_by", property = "updatedBy", jdbcType = JdbcType.VARCHAR),
      @Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP)
  })
  List<UserGroupResources> selectMany(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @ResultMap("UserGroupResourcesResult")
  Optional<UserGroupResources> selectOne(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default long count(CountDSLCompleter completer) {
    return MyBatis3Utils.countFrom(this::count, userGroupResources, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int delete(DeleteDSLCompleter completer) {
    return MyBatis3Utils.deleteFrom(this::delete, userGroupResources, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int deleteByPrimaryKey(String id_) {
    return delete(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insert(UserGroupResources row) {
    return MyBatis3Utils.insert(this::insert, row, userGroupResources, c ->
        c.map(id).toProperty("id")
            .map(userGroupId).toProperty("userGroupId")
            .map(resourceName).toProperty("resourceName")
            .map(allowedActions).toProperty("allowedActions")
            .map(createdBy).toProperty("createdBy")
            .map(createdAt).toProperty("createdAt")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedAt).toProperty("updatedAt")
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insertMultiple(Collection<UserGroupResources> records) {
    return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userGroupResources, c ->
        c.map(id).toProperty("id")
            .map(userGroupId).toProperty("userGroupId")
            .map(resourceName).toProperty("resourceName")
            .map(allowedActions).toProperty("allowedActions")
            .map(createdBy).toProperty("createdBy")
            .map(createdAt).toProperty("createdAt")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedAt).toProperty("updatedAt")
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insertSelective(UserGroupResources row) {
    return MyBatis3Utils.insert(this::insert, row, userGroupResources, c ->
        c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(userGroupId).toPropertyWhenPresent("userGroupId", row::getUserGroupId)
            .map(resourceName).toPropertyWhenPresent("resourceName", row::getResourceName)
            .map(allowedActions).toPropertyWhenPresent("allowedActions", row::getAllowedActions)
            .map(createdBy).toPropertyWhenPresent("createdBy", row::getCreatedBy)
            .map(createdAt).toPropertyWhenPresent("createdAt", row::getCreatedAt)
            .map(updatedBy).toPropertyWhenPresent("updatedBy", row::getUpdatedBy)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", row::getUpdatedAt)
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default Optional<UserGroupResources> selectOne(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectOne(this::selectOne, selectList, userGroupResources, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default List<UserGroupResources> select(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectList(this::selectMany, selectList, userGroupResources, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default List<UserGroupResources> selectDistinct(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userGroupResources,
        completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default Optional<UserGroupResources> selectByPrimaryKey(String id_) {
    return selectOne(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int update(UpdateDSLCompleter completer) {
    return MyBatis3Utils.update(this::update, userGroupResources, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int updateByPrimaryKey(UserGroupResources row) {
    return update(c ->
        c.set(userGroupId).equalTo(row::getUserGroupId)
            .set(resourceName).equalTo(row::getResourceName)
            .set(allowedActions).equalTo(row::getAllowedActions)
            .set(createdBy).equalTo(row::getCreatedBy)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedBy).equalTo(row::getUpdatedBy)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .where(id, isEqualTo(row::getId))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int updateByPrimaryKeySelective(UserGroupResources row) {
    return update(c ->
        c.set(userGroupId).equalToWhenPresent(row::getUserGroupId)
            .set(resourceName).equalToWhenPresent(row::getResourceName)
            .set(allowedActions).equalToWhenPresent(row::getAllowedActions)
            .set(createdBy).equalToWhenPresent(row::getCreatedBy)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .where(id, isEqualTo(row::getId))
    );
  }
}