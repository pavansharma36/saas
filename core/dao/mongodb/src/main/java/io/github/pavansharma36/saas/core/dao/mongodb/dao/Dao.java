package io.github.pavansharma36.saas.core.dao.mongodb.dao;

import io.github.pavansharma36.saas.core.dao.mongodb.model.BaseMongoDbModel;
import java.util.Optional;

public interface Dao<T extends BaseMongoDbModel> {

  T insert(T model);

  T update(T model);

  T save(T model);

  Optional<T> findById(String id);

  T findByIdOrThrow(String id);

}
