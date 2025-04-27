package io.github.pavansharma36.core.common.mutex.dao;

public interface LockDao<T extends LockModel> {

  String insert(T model) throws DuplicateModelException;

  boolean remove(String lockId);

  void extendCurrentProcessLocks();

}
