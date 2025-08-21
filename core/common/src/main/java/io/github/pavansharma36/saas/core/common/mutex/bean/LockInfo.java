package io.github.pavansharma36.saas.core.common.mutex.bean;

import io.github.pavansharma36.saas.core.common.mutex.service.LockService;
import java.io.Closeable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
@RequiredArgsConstructor
public class LockInfo implements Closeable {

  private final String lockId;
  private final Lock lock;

  @ToString.Exclude
  private final transient LockService lockService;

  @Override
  public void close() {
    if (lock.type() == LockType.EXTENSIBLE) {
      boolean value = lockService.releaseLock(this);
      log.info("Release lock {}, response : {}", this, value);
    } else {
      log.info("Ignoring release lock as type is not extensible {}", lock);
    }
  }
}
