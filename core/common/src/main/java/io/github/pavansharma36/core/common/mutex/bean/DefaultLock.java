package io.github.pavansharma36.core.common.mutex.bean;

import java.time.Duration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
@ToString
public class DefaultLock implements Lock {
  @Accessors(fluent = false)
  private final String name;
  private final Duration acquireTimeout;
  private final LockType type;
  private final int maxCount;
  private final Duration duration;

  public static DefaultLockBuilder builder() {
    return new DefaultLockBuilder();
  }

  public static class DefaultLockBuilder {
    private Duration duration = Duration.ofSeconds(60L);
    private Duration acquireTimeout = Duration.ofSeconds(3);
    private LockType type = LockType.EXTENSIBLE;
    private int maxCount = 1;
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

    public DefaultLockBuilder duration(Duration duration) {
      this.duration = duration;
      return this;
    }

    public DefaultLockBuilder acquireTimeout(Duration acquireTimeout) {
      this.acquireTimeout = acquireTimeout;
      return this;
    }

    public DefaultLock build() {
      return new DefaultLock(name, acquireTimeout, type, maxCount, duration);
    }
  }
}
