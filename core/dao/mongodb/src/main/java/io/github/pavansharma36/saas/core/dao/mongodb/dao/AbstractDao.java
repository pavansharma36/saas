package io.github.pavansharma36.saas.core.dao.mongodb.dao;

import io.github.pavansharma36.core.common.context.providers.UserContextProvider;
import io.github.pavansharma36.saas.core.dao.mongodb.model.MongoDbModel;
import java.util.Date;
import org.springframework.data.mongodb.core.MongoTemplate;

public class AbstractDao<T extends MongoDbModel> extends AbstractBaseDao<T> {

  public AbstractDao(Class<T> clazz,
                     MongoTemplate mongoTemplate) {
    super(clazz, mongoTemplate);
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
