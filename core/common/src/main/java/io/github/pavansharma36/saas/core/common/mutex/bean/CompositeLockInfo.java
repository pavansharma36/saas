package io.github.pavansharma36.saas.core.common.mutex.bean;

import io.github.pavansharma36.saas.core.common.mutex.service.LockService;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CompositeLockInfo implements Closeable {

  private final Map<String, Lock> lockIdMap;

  @ToString.Exclude
  private final transient LockService lockService;

  public static CompositeLockInfo build(LockService lockService) {
    return new CompositeLockInfo(new HashMap<>(), lockService);
  }

  public void addLock(LockInfo lockInfo) {
    lockIdMap.put(lockInfo.getLockId(), lockInfo.getLock());
  }

  @Override
  public void close() throws IOException {
    lockService.releaseLock(this);
  }
}
