package io.github.pavansharma36.saas.core.common.mutex.service;

import io.github.pavansharma36.saas.core.common.mutex.bean.CompositeLockInfo;
import io.github.pavansharma36.saas.core.common.mutex.bean.Lock;
import io.github.pavansharma36.saas.core.common.mutex.bean.LockInfo;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface LockService {

  Optional<LockInfo> acquireLock(Lock lock);

  Optional<CompositeLockInfo> acquireLock(List<Lock> locks) throws IOException;

  boolean releaseLock(LockInfo lockInfo);

  boolean releaseLock(CompositeLockInfo lockInfo);

  void extendCurrentProcessLocks();

}
