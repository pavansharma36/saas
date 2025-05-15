package io.github.pavansharma36.saas.core.dao.mongodb.dao;

import io.github.pavansharma36.core.common.utils.CoreUtils;
import io.github.pavansharma36.saas.core.dao.common.dao.Dao;
import io.github.pavansharma36.saas.core.dao.mongodb.model.BaseMongoModel;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import java.util.Date;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class AbstractBaseDao<T extends BaseMongoModel> implements Dao<T> {

  protected final Class<T> clazz;
  protected final MongoTemplate mongoTemplate;

  public AbstractBaseDao(Class<T> clazz, MongoTemplate mongoTemplate) {
    this.clazz = clazz;
    this.mongoTemplate = mongoTemplate;
  }

  protected void preInsert(T model) {
    if (model.getCreatedAt() == null) {
      model.setCreatedAt(new Date());
    }
    if (model.getCreatedBy() == null) {
      model.setCreatedBy(CoreUtils.getUserId());
    }
  }

  @Override
  public T insert(T model) {
    preInsert(model);
    return mongoTemplate.insert(model);
  }

  protected void preUpdate(T model) {
    throw new ServerRuntimeException("Entity not supported for update " + clazz.getName());
  }

  @Override
  public T update(T model) {
    preUpdate(model);
    return mongoTemplate.save(model);
  }

  @Override
  public T save(T model) {
    if (model.getId() == null) {
      return insert(model);
    } else {
      return update(model);
    }
  }

  @Override
  public Optional<T> findById(String id) {
    return Optional.ofNullable(mongoTemplate.findById(new ObjectId(id), clazz));
  }

  @Override
  public T findByIdOrThrow(String id) {
    return findById(id).orElseThrow(
        () -> new ServerRuntimeException("Not able to find entity by id " + id));
  }

  @Override
  public boolean deleteById(String id) {
    Criteria criteria = Criteria.where(BaseMongoModel.FIELD_ID).is(id);
    return mongoTemplate.remove(new Query(criteria), clazz).wasAcknowledged();
  }
}
