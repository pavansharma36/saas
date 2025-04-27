package io.github.pavansharma36.core.common.mutex;

import io.github.pavansharma36.core.common.mutex.service.LockService;
import java.io.Closeable;
import java.io.IOException;
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
  private final LockService lockService;

  @Override
  public void close() throws IOException {
    boolean value = lockService.releaseLock(this);
    log.info("Release lock {}, response : {}", this, value);
  }
}
