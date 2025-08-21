package io.github.pavansharma36.saas.core.dao.mybatis.dao;

import io.github.pavansharma36.saas.core.common.id.IdGenerator;
import io.github.pavansharma36.saas.core.dao.common.EntityListener;
import io.github.pavansharma36.saas.core.dao.common.dao.Dao;
import io.github.pavansharma36.saas.core.dao.common.model.Model;
import io.github.pavansharma36.saas.core.dao.mybatis.listener.MyBatisEntityListener;
import io.github.pavansharma36.saas.core.dao.mybatis.mapper.BaseMapper;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

public abstract class AbstractMyBatisDao<T extends Model, M extends BaseMapper<T>>
    implements Dao<T> {

  private final List<EntityListener<T>> entityListeners;
  private final M mapper;

  protected AbstractMyBatisDao(Class<T> clazz, IdGenerator idGenerator, M mapper) {
    this(Collections.singletonList(new MyBatisEntityListener<>(clazz, idGenerator)), mapper);
  }

  protected AbstractMyBatisDao(List<EntityListener<T>> entityListeners, M mapper) {
    this.entityListeners = entityListeners;
    this.mapper = mapper;
  }

  public T insert(T model) {
    entityListeners.forEach(l -> l.preInsert(model));
    mapper.insert(model);
    return model;
  }

  public T update(T model) {
    entityListeners.forEach(l -> l.preUpdate(model));
    mapper.updateByPrimaryKey(model);
    return model;
  }

  public Optional<T> findById(String id) {
    return mapper.selectByPrimaryKey(id);
  }

  @Override
  public boolean deleteById(String id) {
    entityListeners.forEach(l -> l.preDelete(id));
    return mapper.deleteByPrimaryKey(id) > 0;
  }

  protected List<T> select(SelectDSLCompleter completer) {
    return mapper.select(completer);
  }

  protected Optional<T> selectOne(SelectDSLCompleter completer) {
    return mapper.selectOne(completer);
  }
}
