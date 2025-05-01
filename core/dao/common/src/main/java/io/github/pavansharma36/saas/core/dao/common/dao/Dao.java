package io.github.pavansharma36.saas.core.dao.common.dao;

import io.github.pavansharma36.saas.core.dao.common.model.Model;
import java.util.Optional;

public interface Dao<T extends Model> {
  T insert(T model);

  T update(T model);

  T save(T model);

  Optional<T> findById(String id);

  T findByIdOrThrow(String id);

  boolean deleteById(String id);
}
