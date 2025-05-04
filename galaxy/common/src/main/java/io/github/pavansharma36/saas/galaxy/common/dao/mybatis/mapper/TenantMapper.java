package io.github.pavansharma36.saas.galaxy.common.dao.mybatis.mapper;

import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.TenantDynamicSqlSupport.code;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.TenantDynamicSqlSupport.createdAt;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.TenantDynamicSqlSupport.createdBy;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.TenantDynamicSqlSupport.description;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.TenantDynamicSqlSupport.id;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.TenantDynamicSqlSupport.incrementalId;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.TenantDynamicSqlSupport.name;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.TenantDynamicSqlSupport.tenant;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.TenantDynamicSqlSupport.updatedAt;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.TenantDynamicSqlSupport.updatedBy;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import io.github.pavansharma36.saas.core.dao.mybatis.mapper.BaseMapper;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model.Tenant;
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
public interface TenantMapper
    extends BaseMapper<Tenant>, CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Tenant>,
    CommonUpdateMapper {
  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  BasicColumn[] selectList =
      BasicColumn.columnList(id, name, description, code, incrementalId, createdBy, createdAt,
          updatedBy, updatedAt);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  static UpdateDSL<UpdateModel> updateAllColumns(Tenant row, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalTo(row::getId)
        .set(name).equalTo(row::getName)
        .set(description).equalTo(row::getDescription)
        .set(code).equalTo(row::getCode)
        .set(incrementalId).equalTo(row::getIncrementalId)
        .set(createdBy).equalTo(row::getCreatedBy)
        .set(createdAt).equalTo(row::getCreatedAt)
        .set(updatedBy).equalTo(row::getUpdatedBy)
        .set(updatedAt).equalTo(row::getUpdatedAt);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  static UpdateDSL<UpdateModel> updateSelectiveColumns(Tenant row, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalToWhenPresent(row::getId)
        .set(name).equalToWhenPresent(row::getName)
        .set(description).equalToWhenPresent(row::getDescription)
        .set(code).equalToWhenPresent(row::getCode)
        .set(incrementalId).equalToWhenPresent(row::getIncrementalId)
        .set(createdBy).equalToWhenPresent(row::getCreatedBy)
        .set(createdAt).equalToWhenPresent(row::getCreatedAt)
        .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
        .set(updatedAt).equalToWhenPresent(row::getUpdatedAt);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "TenantResult", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
      @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
      @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
      @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
      @Result(column = "incremental_id", property = "incrementalId", jdbcType = JdbcType.INTEGER),
      @Result(column = "created_by", property = "createdBy", jdbcType = JdbcType.VARCHAR),
      @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "updated_by", property = "updatedBy", jdbcType = JdbcType.VARCHAR),
      @Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP)
  })
  List<Tenant> selectMany(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @ResultMap("TenantResult")
  Optional<Tenant> selectOne(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default long count(CountDSLCompleter completer) {
    return MyBatis3Utils.countFrom(this::count, tenant, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int delete(DeleteDSLCompleter completer) {
    return MyBatis3Utils.deleteFrom(this::delete, tenant, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int deleteByPrimaryKey(String id_) {
    return delete(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insert(Tenant row) {
    return MyBatis3Utils.insert(this::insert, row, tenant, c ->
        c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(description).toProperty("description")
            .map(code).toProperty("code")
            .map(incrementalId).toProperty("incrementalId")
            .map(createdBy).toProperty("createdBy")
            .map(createdAt).toProperty("createdAt")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedAt).toProperty("updatedAt")
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insertMultiple(Collection<Tenant> records) {
    return MyBatis3Utils.insertMultiple(this::insertMultiple, records, tenant, c ->
        c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(description).toProperty("description")
            .map(code).toProperty("code")
            .map(incrementalId).toProperty("incrementalId")
            .map(createdBy).toProperty("createdBy")
            .map(createdAt).toProperty("createdAt")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedAt).toProperty("updatedAt")
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insertSelective(Tenant row) {
    return MyBatis3Utils.insert(this::insert, row, tenant, c ->
        c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(name).toPropertyWhenPresent("name", row::getName)
            .map(description).toPropertyWhenPresent("description", row::getDescription)
            .map(code).toPropertyWhenPresent("code", row::getCode)
            .map(incrementalId).toPropertyWhenPresent("incrementalId", row::getIncrementalId)
            .map(createdBy).toPropertyWhenPresent("createdBy", row::getCreatedBy)
            .map(createdAt).toPropertyWhenPresent("createdAt", row::getCreatedAt)
            .map(updatedBy).toPropertyWhenPresent("updatedBy", row::getUpdatedBy)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", row::getUpdatedAt)
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default Optional<Tenant> selectOne(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectOne(this::selectOne, selectList, tenant, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default List<Tenant> select(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectList(this::selectMany, selectList, tenant, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default List<Tenant> selectDistinct(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectDistinct(this::selectMany, selectList, tenant, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default Optional<Tenant> selectByPrimaryKey(String id_) {
    return selectOne(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int update(UpdateDSLCompleter completer) {
    return MyBatis3Utils.update(this::update, tenant, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int updateByPrimaryKey(Tenant row) {
    return update(c ->
        c.set(name).equalTo(row::getName)
            .set(description).equalTo(row::getDescription)
            .set(code).equalTo(row::getCode)
            .set(incrementalId).equalTo(row::getIncrementalId)
            .set(createdBy).equalTo(row::getCreatedBy)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedBy).equalTo(row::getUpdatedBy)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .where(id, isEqualTo(row::getId))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int updateByPrimaryKeySelective(Tenant row) {
    return update(c ->
        c.set(name).equalToWhenPresent(row::getName)
            .set(description).equalToWhenPresent(row::getDescription)
            .set(code).equalToWhenPresent(row::getCode)
            .set(incrementalId).equalToWhenPresent(row::getIncrementalId)
            .set(createdBy).equalToWhenPresent(row::getCreatedBy)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .where(id, isEqualTo(row::getId))
    );
  }
}