package io.github.pavansharma36.saas.core.common.mutex.dao;

import io.github.pavansharma36.saas.core.common.mutex.bean.LockType;
import java.util.Date;
import java.util.Set;

public interface LockDao<T extends LockModel> {

  T insert(T model) throws DuplicateModelException;

  boolean remove(Set<String> lockIds);

  long updateExpireAtByLockType(LockType lockType, String processUuid, Date expireAt);

}
