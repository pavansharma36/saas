package io.github.pavansharma36.saas.core.dao.mybatis.dao;

import io.github.pavansharma36.saas.core.dao.mybatis.model.BaseMyBatisModel;
import java.util.Optional;

public interface Dao<T extends BaseMyBatisModel> {

  int insert(T model);

  int update(T model);

  int save(T model);

  Optional<T> findById(String id);

  T findByIdOrThrow(String id);

}
