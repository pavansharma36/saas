package io.github.pavansharma36.saas.core.dao.mybatis.mapper;

import io.github.pavansharma36.saas.core.dao.common.model.Model;
import jakarta.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;

public interface BaseMapper<T extends Model>
    extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<T>,
    CommonUpdateMapper {

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  List<T> selectMany(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  Optional<T> selectOne(SelectStatementProvider selectStatement);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  long count(CountDSLCompleter completer);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  int delete(DeleteDSLCompleter completer);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  int deleteByPrimaryKey(String id);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  int insert(T row);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  int insertMultiple(Collection<T> records);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  int insertSelective(T row);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  Optional<T> selectOne(SelectDSLCompleter completer);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  List<T> select(SelectDSLCompleter completer);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  List<T> selectDistinct(SelectDSLCompleter completer);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  Optional<T> selectByPrimaryKey(String id);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  int update(UpdateDSLCompleter completer);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  int updateByPrimaryKey(T row);

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  int updateByPrimaryKeySelective(T row);

}
