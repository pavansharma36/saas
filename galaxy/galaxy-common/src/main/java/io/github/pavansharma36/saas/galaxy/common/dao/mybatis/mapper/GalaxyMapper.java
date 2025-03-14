package io.github.pavansharma36.saas.galaxy.common.dao.mybatis.mapper;

import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.GalaxyDynamicSqlSupport.createdAt;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.GalaxyDynamicSqlSupport.createdBy;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.GalaxyDynamicSqlSupport.description;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.GalaxyDynamicSqlSupport.galaxy;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.GalaxyDynamicSqlSupport.id;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.GalaxyDynamicSqlSupport.name;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.GalaxyDynamicSqlSupport.subDomain;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.GalaxyDynamicSqlSupport.token;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.GalaxyDynamicSqlSupport.updatedAt;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.GalaxyDynamicSqlSupport.updatedBy;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.GalaxyDynamicSqlSupport.urlHost;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.GalaxyDynamicSqlSupport.urlPort;
import static io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support.GalaxyDynamicSqlSupport.urlScheme;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import io.github.pavansharma36.saas.core.dao.mybatis.mapper.BaseMapper;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model.Galaxy;
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
public interface GalaxyMapper
    extends BaseMapper<Galaxy>, CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Galaxy>,
    CommonUpdateMapper {
  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  BasicColumn[] selectList =
      BasicColumn.columnList(id, name, description, token, urlScheme, urlHost, urlPort, subDomain,
          createdBy, createdAt, updatedBy, updatedAt);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  static UpdateDSL<UpdateModel> updateAllColumns(Galaxy row, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalTo(row::getId)
        .set(name).equalTo(row::getName)
        .set(description).equalTo(row::getDescription)
        .set(token).equalTo(row::getToken)
        .set(urlScheme).equalTo(row::getUrlScheme)
        .set(urlHost).equalTo(row::getUrlHost)
        .set(urlPort).equalTo(row::getUrlPort)
        .set(subDomain).equalTo(row::getSubDomain)
        .set(createdBy).equalTo(row::getCreatedBy)
        .set(createdAt).equalTo(row::getCreatedAt)
        .set(updatedBy).equalTo(row::getUpdatedBy)
        .set(updatedAt).equalTo(row::getUpdatedAt);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  static UpdateDSL<UpdateModel> updateSelectiveColumns(Galaxy row, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalToWhenPresent(row::getId)
        .set(name).equalToWhenPresent(row::getName)
        .set(description).equalToWhenPresent(row::getDescription)
        .set(token).equalToWhenPresent(row::getToken)
        .set(urlScheme).equalToWhenPresent(row::getUrlScheme)
        .set(urlHost).equalToWhenPresent(row::getUrlHost)
        .set(urlPort).equalToWhenPresent(row::getUrlPort)
        .set(subDomain).equalToWhenPresent(row::getSubDomain)
        .set(createdBy).equalToWhenPresent(row::getCreatedBy)
        .set(createdAt).equalToWhenPresent(row::getCreatedAt)
        .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
        .set(updatedAt).equalToWhenPresent(row::getUpdatedAt);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "GalaxyResult", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
      @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
      @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
      @Result(column = "token", property = "token", jdbcType = JdbcType.VARCHAR),
      @Result(column = "url_scheme", property = "urlScheme", jdbcType = JdbcType.VARCHAR),
      @Result(column = "url_host", property = "urlHost", jdbcType = JdbcType.VARCHAR),
      @Result(column = "url_port", property = "urlPort", jdbcType = JdbcType.INTEGER),
      @Result(column = "sub_domain", property = "subDomain", jdbcType = JdbcType.VARCHAR),
      @Result(column = "created_by", property = "createdBy", jdbcType = JdbcType.VARCHAR),
      @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "updated_by", property = "updatedBy", jdbcType = JdbcType.VARCHAR),
      @Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP)
  })
  List<Galaxy> selectMany(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @ResultMap("GalaxyResult")
  Optional<Galaxy> selectOne(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default long count(CountDSLCompleter completer) {
    return MyBatis3Utils.countFrom(this::count, galaxy, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int delete(DeleteDSLCompleter completer) {
    return MyBatis3Utils.deleteFrom(this::delete, galaxy, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int deleteByPrimaryKey(String id_) {
    return delete(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insert(Galaxy row) {
    return MyBatis3Utils.insert(this::insert, row, galaxy, c ->
        c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(description).toProperty("description")
            .map(token).toProperty("token")
            .map(urlScheme).toProperty("urlScheme")
            .map(urlHost).toProperty("urlHost")
            .map(urlPort).toProperty("urlPort")
            .map(subDomain).toProperty("subDomain")
            .map(createdBy).toProperty("createdBy")
            .map(createdAt).toProperty("createdAt")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedAt).toProperty("updatedAt")
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insertMultiple(Collection<Galaxy> records) {
    return MyBatis3Utils.insertMultiple(this::insertMultiple, records, galaxy, c ->
        c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(description).toProperty("description")
            .map(token).toProperty("token")
            .map(urlScheme).toProperty("urlScheme")
            .map(urlHost).toProperty("urlHost")
            .map(urlPort).toProperty("urlPort")
            .map(subDomain).toProperty("subDomain")
            .map(createdBy).toProperty("createdBy")
            .map(createdAt).toProperty("createdAt")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedAt).toProperty("updatedAt")
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int insertSelective(Galaxy row) {
    return MyBatis3Utils.insert(this::insert, row, galaxy, c ->
        c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(name).toPropertyWhenPresent("name", row::getName)
            .map(description).toPropertyWhenPresent("description", row::getDescription)
            .map(token).toPropertyWhenPresent("token", row::getToken)
            .map(urlScheme).toPropertyWhenPresent("urlScheme", row::getUrlScheme)
            .map(urlHost).toPropertyWhenPresent("urlHost", row::getUrlHost)
            .map(urlPort).toPropertyWhenPresent("urlPort", row::getUrlPort)
            .map(subDomain).toPropertyWhenPresent("subDomain", row::getSubDomain)
            .map(createdBy).toPropertyWhenPresent("createdBy", row::getCreatedBy)
            .map(createdAt).toPropertyWhenPresent("createdAt", row::getCreatedAt)
            .map(updatedBy).toPropertyWhenPresent("updatedBy", row::getUpdatedBy)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", row::getUpdatedAt)
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default Optional<Galaxy> selectOne(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectOne(this::selectOne, selectList, galaxy, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default List<Galaxy> select(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectList(this::selectMany, selectList, galaxy, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default List<Galaxy> selectDistinct(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectDistinct(this::selectMany, selectList, galaxy, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default Optional<Galaxy> selectByPrimaryKey(String id_) {
    return selectOne(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int update(UpdateDSLCompleter completer) {
    return MyBatis3Utils.update(this::update, galaxy, completer);
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int updateByPrimaryKey(Galaxy row) {
    return update(c ->
        c.set(name).equalTo(row::getName)
            .set(description).equalTo(row::getDescription)
            .set(token).equalTo(row::getToken)
            .set(urlScheme).equalTo(row::getUrlScheme)
            .set(urlHost).equalTo(row::getUrlHost)
            .set(urlPort).equalTo(row::getUrlPort)
            .set(subDomain).equalTo(row::getSubDomain)
            .set(createdBy).equalTo(row::getCreatedBy)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedBy).equalTo(row::getUpdatedBy)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .where(id, isEqualTo(row::getId))
    );
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  default int updateByPrimaryKeySelective(Galaxy row) {
    return update(c ->
        c.set(name).equalToWhenPresent(row::getName)
            .set(description).equalToWhenPresent(row::getDescription)
            .set(token).equalToWhenPresent(row::getToken)
            .set(urlScheme).equalToWhenPresent(row::getUrlScheme)
            .set(urlHost).equalToWhenPresent(row::getUrlHost)
            .set(urlPort).equalToWhenPresent(row::getUrlPort)
            .set(subDomain).equalToWhenPresent(row::getSubDomain)
            .set(createdBy).equalToWhenPresent(row::getCreatedBy)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .where(id, isEqualTo(row::getId))
    );
  }
}