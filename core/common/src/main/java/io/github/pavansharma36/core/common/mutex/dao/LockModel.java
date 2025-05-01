package io.github.pavansharma36.core.common.mutex.dao;

import io.github.pavansharma36.core.common.mutex.bean.LockType;
import java.util.Date;

public interface LockModel {

  String getId();

  void setLockName(String lockName);

  void setIndex(int index);

  void setProcessUuid(String processUuid);

  void setLockType(LockType lockType);

  void setExpireAt(Date expireAt);

}
