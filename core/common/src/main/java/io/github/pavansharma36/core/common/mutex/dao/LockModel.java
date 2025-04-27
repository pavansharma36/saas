package io.github.pavansharma36.core.common.mutex.dao;

import io.github.pavansharma36.core.common.mutex.LockType;

public interface LockModel {

  String getId();

  void setLockName(String lockName);

  void setIndex(int index);

  void setProcessUuid(String processUuid);

  void setLockType(LockType lockType);

  void setExpireAt(long expireAt);

}
