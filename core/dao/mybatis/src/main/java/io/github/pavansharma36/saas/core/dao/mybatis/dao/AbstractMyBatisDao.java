package io.github.pavansharma36.saas.core.dao.mybatis.dao;

import io.github.pavansharma36.core.common.id.IdGenerator;
import io.github.pavansharma36.core.common.utils.CoreUtils;
import io.github.pavansharma36.saas.core.dao.common.dao.Dao;
import io.github.pavansharma36.saas.core.dao.common.model.Model;
import io.github.pavansharma36.saas.core.dao.common.model.UpdatableModel;
import io.github.pavansharma36.saas.core.dao.mybatis.mapper.BaseMapper;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

public abstract class AbstractMyBatisDao<T extends Model, M extends BaseMapper<T>>
    implements Dao<T> {

  private final Class<T> clazz;
  @Getter
  private final IdGenerator idGenerator;
  private final M mapper;

  protected AbstractMyBatisDao(Class<T> clazz, IdGenerator idGenerator, M mapper) {
    this.clazz = clazz;
    this.idGenerator = idGenerator;
    this.mapper = mapper;
  }

  protected void preInsert(T model) {
    if (model.getId() == null) {
      model.setId(idGenerator.nextId(clazz));
    }
    if (model.getCreatedAt() == null) {
      model.setCreatedAt(new Date());
    }
    if (model.getCreatedBy() == null) {
      model.setCreatedBy(CoreUtils.getUserIdOrThrow());
    }
    if (model instanceof UpdatableModel m) {
      if (m.getUpdatedAt() == null) {
        m.setUpdatedAt(new Date());
      }
      if (m.getUpdatedBy() == null) {
        m.setUpdatedBy(CoreUtils.getUserIdOrThrow());
      }
    }
  }

  public T insert(T model) {
    preInsert(model);
    mapper.insert(model);
    return model;
  }

  protected void preUpdate(T model) {
    if (model instanceof UpdatableModel m) {
      m.setUpdatedAt(new Date());
      m.setUpdatedBy(CoreUtils.getUserIdOrThrow());
    } else {
      throw new ServerRuntimeException("Entity not supported for update " + clazz.getName());
    }
  }

  public T update(T model) {
    preUpdate(model);
    mapper.updateByPrimaryKey(model);
    return model;
  }

  public T save(T model) {
    return model.getId() == null ? insert(model) : update(model);
  }

  public Optional<T> findById(String id) {
    return mapper.selectByPrimaryKey(id);
  }

  public T findByIdOrThrow(String id) {
    return findById(id).orElseThrow(
        () -> new ServerRuntimeException("Not able to find entity by id " + id));
  }

  @Override
  public boolean deleteById(String id) {
    return mapper.deleteByPrimaryKey(id) > 0;
  }

  protected List<T> select(SelectDSLCompleter completer) {
    return mapper.select(completer);
  }

  protected Optional<T> selectOne(SelectDSLCompleter completer) {
    return mapper.selectOne(completer);
  }
}
