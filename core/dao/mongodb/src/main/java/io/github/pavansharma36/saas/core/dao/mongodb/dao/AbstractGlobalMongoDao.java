package io.github.pavansharma36.saas.core.dao.mongodb.dao;

import io.github.pavansharma36.saas.core.dao.mongodb.model.BaseMongoModel;
import org.springframework.data.mongodb.core.MongoTemplate;

public class AbstractGlobalMongoDao<T extends BaseMongoModel> extends AbstractMongoDao<T> {

  private final MongoTemplate mongoTemplate;

  protected AbstractGlobalMongoDao(Class<T> clazz, MongoTemplate mongoTemplate) {
    super(clazz);
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  protected MongoTemplate mongoTemplate() {
    return mongoTemplate;
  }
}
