package io.github.pavansharma36.saas.core.dao.common;

import io.github.pavansharma36.saas.core.dao.common.model.Model;

public interface EntityListener<M extends Model> {

  void preInsert(M model);

  void preUpdate(M model);

  void preDelete(String id);

}
