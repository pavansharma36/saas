package io.github.pavansharma36.saas.auth.common.dao.mybatis.mapper;

import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupMapDynamicSqlSupport.createdAt;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupMapDynamicSqlSupport.createdBy;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupMapDynamicSqlSupport.id;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupMapDynamicSqlSupport.userGroupId;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupMapDynamicSqlSupport.userGroupMap;
import static io.github.pavansharma36.saas.auth.common.dao.mybatis.support.UserGroupMapDynamicSqlSupport.userId;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import io.github.pavansharma36.saas.auth.common.dao.mybatis.model.UserGroupMap;
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
public interface UserGroupMapMapper
    extends BaseMapper<UserGroupMap>, CommonCountMapper, CommonDeleteMapper,
    CommonInsertMapper<UserGroupMap>, CommonUpdateMapper {
  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  BasicColumn[] selectList = BasicColumn.columnList(id, userId, userGroupId, createdBy, createdAt);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  static UpdateDSL<UpdateModel> updateAllColumns(UserGroupMap row, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalTo(row::getId)
        .set(userId).equalTo(row::getUserId)
        .set(userGroupId).equalTo(row::getUserGroupId)
        .set(createdBy).equalTo(row::getCreatedBy)
        .set(createdAt).equalTo(row::getCreatedAt);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  static UpdateDSL<UpdateModel> updateSelectiveColumns(UserGroupMap row,
                                                       UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalToWhenPresent(row::getId)
        .set(userId).equalToWhenPresent(row::getUserId)
        .set(userGroupId).equalToWhenPresent(row::getUserGroupId)
        .set(createdBy).equalToWhenPresent(row::getCreatedBy)
        .set(createdAt).equalToWhenPresent(row::getCreatedAt);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "UserGroupMapResult", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
      @Result(column = "user_id", property = "userId", jdbcType = JdbcType.VARCHAR),
      @Result(column = "user_group_id", property = "userGroupId", jdbcType = JdbcType.VARCHAR),
      @Result(column = "created_by", property = "createdBy", jdbcType = JdbcType.VARCHAR),
      @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP)
  })
  List<UserGroupMap> selectMany(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @ResultMap("UserGroupMapResult")
  Optional<UserGroupMap> selectOne(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default long count(CountDSLCompleter completer) {
    return MyBatis3Utils.countFrom(this::count, userGroupMap, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int delete(DeleteDSLCompleter completer) {
    return MyBatis3Utils.deleteFrom(this::delete, userGroupMap, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int deleteByPrimaryKey(String id_) {
    return delete(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insert(UserGroupMap row) {
    return MyBatis3Utils.insert(this::insert, row, userGroupMap, c ->
        c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(userGroupId).toProperty("userGroupId")
            .map(createdBy).toProperty("createdBy")
            .map(createdAt).toProperty("createdAt")
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insertMultiple(Collection<UserGroupMap> records) {
    return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userGroupMap, c ->
        c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(userGroupId).toProperty("userGroupId")
            .map(createdBy).toProperty("createdBy")
            .map(createdAt).toProperty("createdAt")
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insertSelective(UserGroupMap row) {
    return MyBatis3Utils.insert(this::insert, row, userGroupMap, c ->
        c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(userId).toPropertyWhenPresent("userId", row::getUserId)
            .map(userGroupId).toPropertyWhenPresent("userGroupId", row::getUserGroupId)
            .map(createdBy).toPropertyWhenPresent("createdBy", row::getCreatedBy)
            .map(createdAt).toPropertyWhenPresent("createdAt", row::getCreatedAt)
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default Optional<UserGroupMap> selectOne(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectOne(this::selectOne, selectList, userGroupMap, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default List<UserGroupMap> select(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectList(this::selectMany, selectList, userGroupMap, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default List<UserGroupMap> selectDistinct(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userGroupMap, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default Optional<UserGroupMap> selectByPrimaryKey(String id_) {
    return selectOne(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int update(UpdateDSLCompleter completer) {
    return MyBatis3Utils.update(this::update, userGroupMap, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int updateByPrimaryKey(UserGroupMap row) {
    return update(c ->
        c.set(userId).equalTo(row::getUserId)
            .set(userGroupId).equalTo(row::getUserGroupId)
            .set(createdBy).equalTo(row::getCreatedBy)
            .set(createdAt).equalTo(row::getCreatedAt)
            .where(id, isEqualTo(row::getId))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int updateByPrimaryKeySelective(UserGroupMap row) {
    return update(c ->
        c.set(userId).equalToWhenPresent(row::getUserId)
            .set(userGroupId).equalToWhenPresent(row::getUserGroupId)
            .set(createdBy).equalToWhenPresent(row::getCreatedBy)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .where(id, isEqualTo(row::getId))
    );
  }
}