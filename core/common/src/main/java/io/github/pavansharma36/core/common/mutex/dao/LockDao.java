package io.github.pavansharma36.core.common.mutex.dao;

import io.github.pavansharma36.core.common.mutex.bean.LockType;

public interface LockDao<T extends LockModel> {

  String insert(T model) throws DuplicateModelException;

  boolean remove(String lockId);

  void updateExpireAtByLockType(LockType lockType, String processUuid, long expireAt);

}
