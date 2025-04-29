package io.github.pavansharma36.core.common.mutex.bean;

import io.github.pavansharma36.saas.utils.Named;

public interface Lock extends Named {

  LockType type();

  int maxCount();

  long durationMillis();

  long acquireTimeout();

}
