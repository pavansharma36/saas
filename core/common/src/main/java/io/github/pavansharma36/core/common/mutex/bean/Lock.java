package io.github.pavansharma36.core.common.mutex.bean;

import io.github.pavansharma36.saas.utils.Named;
import java.time.Duration;

public interface Lock extends Named {

  LockType type();

  int maxCount();

  Duration duration();

  Duration acquireTimeout();

}
