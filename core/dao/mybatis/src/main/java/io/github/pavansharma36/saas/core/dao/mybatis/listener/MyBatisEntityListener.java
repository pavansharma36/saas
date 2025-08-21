package io.github.pavansharma36.saas.core.dao.mybatis.listener;

import io.github.pavansharma36.saas.core.common.id.IdGenerator;
import io.github.pavansharma36.saas.core.dao.common.DefaultEntityListener;
import io.github.pavansharma36.saas.core.dao.common.model.Model;

public class MyBatisEntityListener<M extends Model> extends DefaultEntityListener<M> {

  private final IdGenerator idGenerator;

  public MyBatisEntityListener(Class<M> clazz, IdGenerator idGenerator) {
    super(clazz);
    this.idGenerator = idGenerator;
  }

  @Override
  public void preInsert(M model) {
    super.preInsert(model);
    if (model.getId() == null) {
      model.setId(idGenerator.nextId(clazz));
    }
  }
}
