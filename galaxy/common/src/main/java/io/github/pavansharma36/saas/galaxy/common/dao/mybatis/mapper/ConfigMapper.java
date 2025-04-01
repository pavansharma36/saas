package io.github.pavansharma36.saas.galaxy.common.dao.mybatis.mapper;

import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.ConfigDynamicSqlSupport.classifier;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.ConfigDynamicSqlSupport.classifierValue;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.ConfigDynamicSqlSupport.config;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.ConfigDynamicSqlSupport.configName;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.ConfigDynamicSqlSupport.configValue;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.ConfigDynamicSqlSupport.createdAt;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.ConfigDynamicSqlSupport.createdBy;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.ConfigDynamicSqlSupport.id;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.ConfigDynamicSqlSupport.updatedAt;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.ConfigDynamicSqlSupport.updatedBy;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import io.github.pavansharma36.saas.core.dao.mybatis.mapper.BaseMapper;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model.Config;
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
public interface ConfigMapper
    extends BaseMapper<Config>, CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Config>,
    CommonUpdateMapper {
  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  BasicColumn[] selectList =
      BasicColumn.columnList(id, classifier, classifierValue, configName, configValue, createdBy,
          createdAt, updatedBy, updatedAt);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  static UpdateDSL<UpdateModel> updateAllColumns(Config row, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalTo(row::getId)
        .set(classifier).equalTo(row::getClassifier)
        .set(classifierValue).equalTo(row::getClassifierValue)
        .set(configName).equalTo(row::getConfigName)
        .set(configValue).equalTo(row::getConfigValue)
        .set(createdBy).equalTo(row::getCreatedBy)
        .set(createdAt).equalTo(row::getCreatedAt)
        .set(updatedBy).equalTo(row::getUpdatedBy)
        .set(updatedAt).equalTo(row::getUpdatedAt);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  static UpdateDSL<UpdateModel> updateSelectiveColumns(Config row, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalToWhenPresent(row::getId)
        .set(classifier).equalToWhenPresent(row::getClassifier)
        .set(classifierValue).equalToWhenPresent(row::getClassifierValue)
        .set(configName).equalToWhenPresent(row::getConfigName)
        .set(configValue).equalToWhenPresent(row::getConfigValue)
        .set(createdBy).equalToWhenPresent(row::getCreatedBy)
        .set(createdAt).equalToWhenPresent(row::getCreatedAt)
        .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
        .set(updatedAt).equalToWhenPresent(row::getUpdatedAt);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "ConfigResult", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
      @Result(column = "classifier", property = "classifier", jdbcType = JdbcType.VARCHAR),
      @Result(column = "classifier_value", property = "classifierValue", jdbcType = JdbcType.VARCHAR),
      @Result(column = "config_name", property = "configName", jdbcType = JdbcType.VARCHAR),
      @Result(column = "config_value", property = "configValue", jdbcType = JdbcType.VARCHAR),
      @Result(column = "created_by", property = "createdBy", jdbcType = JdbcType.VARCHAR),
      @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "updated_by", property = "updatedBy", jdbcType = JdbcType.VARCHAR),
      @Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP)
  })
  List<Config> selectMany(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @ResultMap("ConfigResult")
  Optional<Config> selectOne(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default long count(CountDSLCompleter completer) {
    return MyBatis3Utils.countFrom(this::count, config, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int delete(DeleteDSLCompleter completer) {
    return MyBatis3Utils.deleteFrom(this::delete, config, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int deleteByPrimaryKey(String id_) {
    return delete(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insert(Config row) {
    return MyBatis3Utils.insert(this::insert, row, config, c ->
        c.map(id).toProperty("id")
            .map(classifier).toProperty("classifier")
            .map(classifierValue).toProperty("classifierValue")
            .map(configName).toProperty("configName")
            .map(configValue).toProperty("configValue")
            .map(createdBy).toProperty("createdBy")
            .map(createdAt).toProperty("createdAt")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedAt).toProperty("updatedAt")
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insertMultiple(Collection<Config> records) {
    return MyBatis3Utils.insertMultiple(this::insertMultiple, records, config, c ->
        c.map(id).toProperty("id")
            .map(classifier).toProperty("classifier")
            .map(classifierValue).toProperty("classifierValue")
            .map(configName).toProperty("configName")
            .map(configValue).toProperty("configValue")
            .map(createdBy).toProperty("createdBy")
            .map(createdAt).toProperty("createdAt")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedAt).toProperty("updatedAt")
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insertSelective(Config row) {
    return MyBatis3Utils.insert(this::insert, row, config, c ->
        c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(classifier).toPropertyWhenPresent("classifier", row::getClassifier)
            .map(classifierValue).toPropertyWhenPresent("classifierValue", row::getClassifierValue)
            .map(configName).toPropertyWhenPresent("configName", row::getConfigName)
            .map(configValue).toPropertyWhenPresent("configValue", row::getConfigValue)
            .map(createdBy).toPropertyWhenPresent("createdBy", row::getCreatedBy)
            .map(createdAt).toPropertyWhenPresent("createdAt", row::getCreatedAt)
            .map(updatedBy).toPropertyWhenPresent("updatedBy", row::getUpdatedBy)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", row::getUpdatedAt)
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default Optional<Config> selectOne(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectOne(this::selectOne, selectList, config, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default List<Config> select(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectList(this::selectMany, selectList, config, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default List<Config> selectDistinct(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectDistinct(this::selectMany, selectList, config, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default Optional<Config> selectByPrimaryKey(String id_) {
    return selectOne(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int update(UpdateDSLCompleter completer) {
    return MyBatis3Utils.update(this::update, config, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int updateByPrimaryKey(Config row) {
    return update(c ->
        c.set(classifier).equalTo(row::getClassifier)
            .set(classifierValue).equalTo(row::getClassifierValue)
            .set(configName).equalTo(row::getConfigName)
            .set(configValue).equalTo(row::getConfigValue)
            .set(createdBy).equalTo(row::getCreatedBy)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedBy).equalTo(row::getUpdatedBy)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .where(id, isEqualTo(row::getId))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int updateByPrimaryKeySelective(Config row) {
    return update(c ->
        c.set(classifier).equalToWhenPresent(row::getClassifier)
            .set(classifierValue).equalToWhenPresent(row::getClassifierValue)
            .set(configName).equalToWhenPresent(row::getConfigName)
            .set(configValue).equalToWhenPresent(row::getConfigValue)
            .set(createdBy).equalToWhenPresent(row::getCreatedBy)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .where(id, isEqualTo(row::getId))
    );
  }
}