package io.github.pavansharma36.core.common.mutex;

import io.github.pavansharma36.saas.utils.Named;

public enum LockType implements Named {

  FIXED,
  EXTENSIBLE;
  
  @Override
  public String getName() {
    return name();
  }
}
