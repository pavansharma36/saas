package io.github.pavansharma36.core.common.mutex.bean;

import io.github.pavansharma36.core.common.mutex.service.LockService;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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

  private final Set<String> lockIds;
  private final Map<String, Lock> lockIdMap;

  @ToString.Exclude
  private final transient LockService lockService;

  public static CompositeLockInfo build(LockService lockService) {
    return new CompositeLockInfo(new HashSet<>(), new HashMap<>(), lockService);
  }

  public void addLock(LockInfo lockInfo) {
    lockIds.add(lockInfo.getLockId());
    lockIdMap.put(lockInfo.getLockId(), lockInfo.getLock());
  }

  @Override
  public void close() throws IOException {
    lockService.releaseLock(this);
  }
}
