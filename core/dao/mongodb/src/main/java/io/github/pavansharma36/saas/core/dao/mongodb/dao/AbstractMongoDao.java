package io.github.pavansharma36.saas.core.dao.mongodb.dao;

import com.mongodb.client.result.UpdateResult;
import io.github.pavansharma36.saas.core.dao.common.DefaultEntityListener;
import io.github.pavansharma36.saas.core.dao.common.EntityListener;
import io.github.pavansharma36.saas.core.dao.common.dao.Dao;
import io.github.pavansharma36.saas.core.dao.mongodb.model.BaseMongoModel;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public abstract class AbstractMongoDao<T extends BaseMongoModel> implements Dao<T> {

  private final Class<T> clazz;
  private final List<EntityListener<T>> entityListeners;

  protected AbstractMongoDao(Class<T> clazz) {
    this(clazz, Collections.singletonList(new DefaultEntityListener<>(clazz)));
  }

  protected AbstractMongoDao(Class<T> clazz, List<EntityListener<T>> entityListeners) {
    this.clazz = clazz;
    this.entityListeners = entityListeners;
  }

  protected abstract MongoTemplate mongoTemplate();

  @Override
  public T insert(T model) {
    entityListeners.forEach(l -> l.preInsert(model));
    return mongoTemplate().insert(model);
  }

  @Override
  public T update(T model) {
    entityListeners.forEach(l -> l.preUpdate(model));
    return mongoTemplate().save(model);
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
    return Optional.ofNullable(mongoTemplate().findById(new ObjectId(id), clazz));
  }

  @Override
  public T findByIdOrThrow(String id) {
    return findById(id).orElseThrow(
        () -> new ServerRuntimeException("Not able to find entity by id " + id));
  }

  @Override
  public boolean deleteById(String id) {
    entityListeners.forEach(l -> l.preDelete(id));
    Criteria criteria = Criteria.where(BaseMongoModel.FIELD_ID).is(id);
    return mongoTemplate().remove(new Query(criteria), clazz).wasAcknowledged();
  }

  protected boolean deleteByQuery(Query query) {
    return mongoTemplate().remove(query, clazz).wasAcknowledged();
  }

  protected UpdateResult updateMulti(Query query, Update update) {
    return mongoTemplate().updateMulti(query, update, clazz);
  }

  protected boolean exists(Query query) {
    return mongoTemplate().exists(query, clazz);
  }
}
