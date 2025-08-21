package io.github.pavansharma36.saas.core.common.storage;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractStorageService implements StorageService {

  private final StorageType type;

  @Override
  public StorageType type() {
    return type;
  }
  
}
