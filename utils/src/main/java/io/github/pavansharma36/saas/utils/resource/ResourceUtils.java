package io.github.pavansharma36.saas.utils.resource;

import io.github.pavansharma36.saas.utils.ex.AppConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ResourceUtils {

  public static Optional<File> findFile(String relativePath, int uptoParent) {
    while (uptoParent >= 0) {
      File file = new File(relativePath);
      if (file.exists()) {
        return Optional.of(file);
      }
      relativePath = "../" + relativePath; // NOSONAR string builder not required.
      uptoParent--;
    }
    return Optional.empty();
  }

  public static File findFileOrThrow(String relativePath, int uptoParent) {
    return findFile(relativePath, uptoParent)
        .orElseThrow(() -> new AppConfigurationException("Not able to find conf " + relativePath));
  }

  public static Properties properties(File file) {
    Properties props = new Properties();
    try (InputStream in = new FileInputStream(file)) {
      props.load(in);
    } catch (IOException e) {
      throw new AppConfigurationException(e.getMessage(), e);
    }
    return props;
  }

}
