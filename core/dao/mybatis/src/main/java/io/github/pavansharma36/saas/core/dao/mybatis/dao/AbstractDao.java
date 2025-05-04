package io.github.pavansharma36.saas.core.dao.mybatis.dao;

import io.github.pavansharma36.core.common.context.providers.UserContextProvider;
import io.github.pavansharma36.core.common.id.IdGenerator;
import io.github.pavansharma36.saas.core.dao.common.model.UpdatableModel;
import io.github.pavansharma36.saas.core.dao.mybatis.mapper.BaseMapper;
import java.util.Date;

public class AbstractDao<T extends UpdatableModel, M extends BaseMapper<T>>
    extends AbstractBaseDao<T, M> {

  protected AbstractDao(Class<T> clazz,
                        IdGenerator idGenerator,
                        M mapper) {
    super(clazz, idGenerator, mapper);
  }

  @Override
  protected void preInsert(T model) {
    super.preInsert(model);
    if (model.getUpdatedAt() == null) {
      model.setUpdatedAt(new Date());
    }
    if (model.getUpdatedBy() == null) {
      model.setUpdatedBy(UserContextProvider.getInstance().getOrThrow().getId());
    }
  }

  @Override
  protected void preUpdate(T model) {
    model.setUpdatedAt(new Date());
    model.setUpdatedBy(UserContextProvider.getInstance().getOrThrow().getId());
  }
}
