package io.github.pavansharma36.saas.galaxy.common.dao.mybatis.dao;

import io.github.pavansharma36.core.common.id.IdGenerator;
import io.github.pavansharma36.saas.core.dao.common.Model;
import io.github.pavansharma36.saas.core.dao.mybatis.model.MyBatisModel;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.mapper.BaseMapper;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import java.util.Date;
import java.util.Optional;

public abstract class AbstractDAO<T extends Model, M extends BaseMapper<T>> {

  protected final Class<T> clazz;
  protected final IdGenerator idGenerator;
  protected final M mapper;

  protected AbstractDAO(Class<T> clazz, IdGenerator idGenerator, M mapper) {
    this.clazz = clazz;
    this.idGenerator = idGenerator;
    this.mapper = mapper;
  }

  public int insert(T model) {
    if (model.getId() == null) {
      model.setId(idGenerator.nextId(clazz));
    }
    if (model.getCreatedAt() == null) {
      model.setCreatedAt(new Date());
    }
    if (model instanceof MyBatisModel m) {
      if (m.getUpdatedAt() == null) {
        m.setUpdatedAt(new Date());
      }
    }
    return mapper.insert(model);
  }

  public int update(T model) {
    if (model instanceof MyBatisModel m) {
      m.setUpdatedAt(new Date());
    } else {
      throw new ServerRuntimeException("Entity not supported for update " + clazz.getName());
    }
    return mapper.updateByPrimaryKey(model);
  }

  public int save(T model) {
    return model.getId() == null ? insert(model) : update(model);
  }

  public Optional<T> findById(String id) {
    return mapper.selectByPrimaryKey(id);
  }

  public T findByIdOrThrow(String id) {
    return findById(id).orElseThrow(
        () -> new ServerRuntimeException("Not able to find entity by id " + id));
  }


}
