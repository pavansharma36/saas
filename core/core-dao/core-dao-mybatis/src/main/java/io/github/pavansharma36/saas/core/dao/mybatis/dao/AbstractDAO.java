package io.github.pavansharma36.saas.core.dao.mybatis.dao;

import io.github.pavansharma36.core.common.id.IdGenerator;
import io.github.pavansharma36.saas.core.dao.mybatis.mapper.BaseMapper;
import io.github.pavansharma36.saas.core.dao.mybatis.model.MyBatisModel;
import java.util.Date;

public class AbstractDAO<T extends MyBatisModel, M extends BaseMapper<T>>
    extends AbstractBaseDAO<T, M> {

  protected AbstractDAO(Class<T> clazz,
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
  }

  @Override
  protected void preUpdate(T model) {
    model.setUpdatedAt(new Date());
  }
}
