package io.github.pavansharma36.saas.core.dao.mybatis.dao;

import io.github.pavansharma36.core.common.context.providers.UserContextProvider;
import io.github.pavansharma36.core.common.id.IdGenerator;
import io.github.pavansharma36.saas.core.dao.common.dao.Dao;
import io.github.pavansharma36.saas.core.dao.mybatis.mapper.BaseMapper;
import io.github.pavansharma36.saas.core.dao.mybatis.model.BaseMyBatisModel;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import java.util.Date;
import java.util.Optional;

public abstract class AbstractBaseDao<T extends BaseMyBatisModel, M extends BaseMapper<T>>
    implements Dao<T> {

  protected final Class<T> clazz;
  protected final IdGenerator idGenerator;
  protected final M mapper;

  protected AbstractBaseDao(Class<T> clazz, IdGenerator idGenerator, M mapper) {
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
      model.setCreatedBy(UserContextProvider.getInstance().getOrThrow().getId());
    }
  }

  public T insert(T model) {
    preInsert(model);
    mapper.insert(model);
    return model;
  }

  protected void preUpdate(T model) {
    throw new ServerRuntimeException("Entity not supported for update " + clazz.getName());
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
}
