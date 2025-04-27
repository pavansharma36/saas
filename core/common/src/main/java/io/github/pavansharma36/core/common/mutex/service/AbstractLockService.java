package io.github.pavansharma36.core.common.mutex.service;

import io.github.pavansharma36.core.common.mutex.Lock;
import io.github.pavansharma36.core.common.mutex.LockInfo;
import io.github.pavansharma36.core.common.mutex.dao.DuplicateModelException;
import io.github.pavansharma36.core.common.mutex.dao.LockDao;
import io.github.pavansharma36.core.common.mutex.dao.LockModel;
import io.github.pavansharma36.core.common.utils.CoreConstants;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractLockService<T extends LockModel> implements LockService {

  private final LockDao<T> lockDao;

  @Override
  public Optional<LockInfo> acquireLock(Lock lock) {
    log.info("Trying to acquire lock {}", lock);
    T model = createModel(lock);
    model.setLockType(lock.type());
    model.setLockName(lock.getName());
    model.setProcessUuid(CoreConstants.PROCESS_UUID);
    int i = 0;
    while (i < lock.maxCount()) {
      model.setIndex(i);
      model.setExpireAt(System.currentTimeMillis() + lock.durationMillis());
      try {
        String lockId = lockDao.insert(model);
        return Optional.of(new LockInfo(lockId, lock, this));
      } catch (DuplicateModelException e) {
        log.debug("Ignoring duplicate key exception while acquiring lock {}", lock);
      }
      ++i;
    }
    return Optional.empty();
  }

  @Override
  public boolean releaseLock(LockInfo lockInfo) {
    log.info("Releasing lock {}", lockInfo);
    return lockDao.remove(lockInfo.getLockId());
  }

  @Override
  public void extendCurrentProcessLocks() {
    lockDao.extendCurrentProcessLocks();
  }

  protected abstract T createModel(Lock lock);

}
