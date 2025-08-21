package io.github.pavansharma36.saas.core.common.storage.local;

import io.github.pavansharma36.saas.core.common.config.Config;
import io.github.pavansharma36.saas.core.common.storage.AbstractStorageService;
import io.github.pavansharma36.saas.core.common.storage.StorageType;
import io.github.pavansharma36.saas.core.common.validation.ServerRuntimeException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

@Service
@Conditional(LocalStorageCondition.class)
public class LocalStorageService extends AbstractStorageService {

  private final String basePath;

  public LocalStorageService() {
    super(StorageType.LOCAL);
    basePath = Config.get("local.storage.basePath", "/tmp");
  }

  @Override
  public boolean exists(String key) {
    return new File(getFullPath(key)).exists();
  }

  @Override
  public boolean delete(String key) {
    return new File(getFullPath(key)).delete();
  }

  @Override
  public void upload(String key, InputStream inputStream) {
    try {
      FileUtils.copyInputStreamToFile(inputStream, new File(getFullPath(key)));
    } catch (IOException e) {
      throw new ServerRuntimeException(e);
    }
  }

  @Override
  public InputStream download(String key) {
    try {
      return new FileInputStream(getFullPath(key));
    } catch (FileNotFoundException e) {
      throw new ServerRuntimeException(e);
    }
  }

  private String getFullPath(String key) {
    return basePath + File.separator + key;
  }
}
