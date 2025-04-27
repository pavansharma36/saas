package io.github.pavansharma36.core.common.mutex.service;

import io.github.pavansharma36.core.common.mutex.Lock;
import io.github.pavansharma36.core.common.mutex.LockInfo;
import java.util.Optional;

public interface LockService {

  Optional<LockInfo> acquireLock(Lock lock);

  boolean releaseLock(LockInfo lockInfo);

  void extendCurrentProcessLocks();

}
