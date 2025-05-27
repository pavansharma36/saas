package io.github.pavansharma36.core.common.mutex.dao;

import io.github.pavansharma36.core.common.mutex.bean.LockType;
import java.util.Date;
import java.util.Set;

public interface LockDao<T extends LockModel> {

  T insert(T model) throws DuplicateModelException;

  boolean remove(Set<String> lockIds);

  void updateExpireAtByLockType(LockType lockType, String processUuid, Date expireAt);

}
