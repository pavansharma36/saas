package io.github.pavansharma36.saas.core.dao.common.dao;

import io.github.pavansharma36.saas.core.common.validation.BadRequestException;
import io.github.pavansharma36.saas.core.common.validation.CoreErrorCode;
import io.github.pavansharma36.saas.core.common.validation.ErrorCodeException;
import io.github.pavansharma36.saas.core.dao.common.model.Model;
import java.util.Collections;
import java.util.Optional;

public interface Dao<T extends Model> {
  T insert(T model);

  T update(T model);

  default T save(T model) {
    return model.getId() == null ? insert(model) : update(model);
  }

  Optional<T> findById(String id);

  default T findByIdOrThrow(String id) {
    return findById(id).orElseThrow(
        () -> new ErrorCodeException(CoreErrorCode.NOT_FOUND, Collections.singletonMap("id", id),
            500));
  }

  default T findByIdOrThrowBadRequest(String id) {
    return findById(id).orElseThrow(() -> new BadRequestException(CoreErrorCode.NOT_FOUND,
        Collections.singletonMap("id", id)));
  }

  boolean deleteById(String id);
}
