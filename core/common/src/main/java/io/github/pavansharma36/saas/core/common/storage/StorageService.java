package io.github.pavansharma36.saas.core.common.storage;

import java.io.InputStream;

public interface StorageService {

  String IMPL_CONF = "storage.impl";

  StorageType type();

  boolean exists(String key);

  boolean delete(String key);

  void upload(String key, InputStream inputStream);

  InputStream download(String key);
}
