package io.github.pavansharma36.saas.core.dao.mongodb.dao;

import io.github.pavansharma36.core.common.utils.CoreUtils;
import io.github.pavansharma36.saas.core.dao.mongodb.model.MongoModel;
import java.util.Date;
import org.springframework.data.mongodb.core.MongoTemplate;

public class AbstractDao<T extends MongoModel> extends AbstractBaseDao<T> {

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
      model.setUpdatedBy(CoreUtils.getUserId());
    }
  }

  @Override
  protected void preUpdate(T model) {
    model.setUpdatedAt(new Date());
    model.setUpdatedBy(CoreUtils.getUserId());
  }
}
