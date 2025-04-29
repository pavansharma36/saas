package io.github.pavansharma36.core.common.mutex.bean;

import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class DefaultLock implements Lock {
  @Accessors(fluent = false)
  private final String name;
  private final long acquireTimeout;
  private final LockType type;
  private final int maxCount;
  private final long durationMillis;

  public static DefaultLockBuilder builder() {
    return new DefaultLockBuilder();
  }

  public static class DefaultLockBuilder {
    private long acquireTimeout = TimeUnit.SECONDS.toMillis(3);
    private LockType type = LockType.EXTENSIBLE;
    private int maxCount = 1;
    private long durationMillis = TimeUnit.SECONDS.toMillis(60L);
    private String name;

    public DefaultLockBuilder name(String name) {
      this.name = name;
      return this;
    }

    public DefaultLockBuilder type(LockType type) {
      this.type = type;
      return this;
    }

    public DefaultLockBuilder maxCount(int maxCount) {
      this.maxCount = maxCount;
      return this;
    }

    public DefaultLockBuilder durationMillis(long durationMillis) {
      this.durationMillis = durationMillis;
      return this;
    }

    public DefaultLockBuilder acquireTimeout(long acquireTimeout) {
      this.acquireTimeout = acquireTimeout;
      return this;
    }

    public DefaultLock build() {
      return new DefaultLock(name, acquireTimeout, type, maxCount, durationMillis);
    }
  }
}
