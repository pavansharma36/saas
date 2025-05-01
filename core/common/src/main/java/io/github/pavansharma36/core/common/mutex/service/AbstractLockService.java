package io.github.pavansharma36.core.common.mutex.service;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.core.common.factory.ExecutorFactory;
import io.github.pavansharma36.core.common.mutex.bean.Lock;
import io.github.pavansharma36.core.common.mutex.bean.LockInfo;
import io.github.pavansharma36.core.common.mutex.bean.LockType;
import io.github.pavansharma36.core.common.mutex.dao.DuplicateModelException;
import io.github.pavansharma36.core.common.mutex.dao.LockDao;
import io.github.pavansharma36.core.common.mutex.dao.LockModel;
import io.github.pavansharma36.core.common.utils.CoreConstants;
import io.github.pavansharma36.saas.utils.Utils;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractLockService<T extends LockModel> implements LockService {

  private final LockDao<T> lockDao;

  protected AbstractLockService(LockDao<T> lockDao) {
    this.lockDao = lockDao;

    int lockExtendScheduleSeconds = Config.getInt("mutex.lock.extend.delay", 30);

    log.info("Scheduling lock extend thread for process {} with delay of {} seconds",
        CoreConstants.PROCESS_UUID, lockExtendScheduleSeconds);
    ExecutorFactory.scheduledExecutorService()
        .scheduleAtFixedRate(this::extendCurrentProcessLocks,
            lockExtendScheduleSeconds, lockExtendScheduleSeconds, TimeUnit.SECONDS);
  }

  @Override
  public Optional<LockInfo> acquireLock(Lock lock) {
    long lockTrySleepMillis = Config.getLong("mutex.lock.try.wait.millis", 300L);
    long lockIndexTryMillis = Config.getLong("mutex.lock.index.wait.millis", 10L);

    long waitTill = System.currentTimeMillis() + lock.acquireTimeout().toMillis();
    long durationMillis = lock.duration().toMillis();

    T model = createModel(lock);
    model.setLockType(lock.type());
    model.setLockName(lock.getName());
    model.setProcessUuid(CoreConstants.PROCESS_UUID);
    int i = 0;
    while (System.currentTimeMillis() < waitTill) {
      log.info("Trying to acquire lock {}: {}", i, lock);
      model.setIndex(i);
      model.setExpireAt(new Date(System.currentTimeMillis() + durationMillis));
      try {
        String lockId = lockDao.insert(model).getId();
        log.info("Acquired lock {}: {} by {}", lockId, lock, CoreConstants.PROCESS_UUID);
        return Optional.of(new LockInfo(lockId, lock, this));
      } catch (DuplicateModelException e) {
        log.debug("Ignoring duplicate key exception while acquiring lock {}", lock);
      }
      if (++i == lock.maxCount()) {
        i = 0;
        Utils.sleepQuietly(lockTrySleepMillis);
      } else {
        Utils.sleepQuietly(lockIndexTryMillis);
      }
    }
    log.warn("Couldn't acquire lock {}", lock);
    return Optional.empty();
  }

  @Override
  public boolean releaseLock(LockInfo lockInfo) {
    log.info("Releasing lock {}", lockInfo);
    return lockDao.remove(lockInfo.getLockId());
  }

  @Override
  public void extendCurrentProcessLocks() {
    int extentLockBySeconds = Config.getInt("mutex.lock.extend.seconds", 60);
    log.debug("Extending all locks of current process {} by {} seconds",
        CoreConstants.PROCESS_UUID, extentLockBySeconds);
    lockDao.updateExpireAtByLockType(LockType.EXTENSIBLE, CoreConstants.PROCESS_UUID,
        new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(extentLockBySeconds)));
  }

  protected abstract T createModel(Lock lock);

}
