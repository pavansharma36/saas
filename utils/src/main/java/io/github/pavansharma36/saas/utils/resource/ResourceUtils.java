package io.github.pavansharma36.saas.utils.resource;

import io.github.pavansharma36.saas.utils.ex.AppConfigurationException;
import io.github.pavansharma36.saas.utils.ex.AppRuntimeException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Properties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;

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

  public static Properties classpathProperties(String fileName, Class<?> clazz) {
    Properties props = new Properties();
    try {
      props.load(clazz.getClassLoader().getResourceAsStream(fileName));
    } catch (IOException e) {
      throw new AppConfigurationException(e.getMessage(), e);
    }
    return props;
  }

  public static String readClasspathFile(String fileName, Class<?> clazz) {
    try {
      return IOUtils.resourceToString(fileName, StandardCharsets.UTF_8, clazz.getClassLoader());
    } catch (IOException e) {
      throw new AppRuntimeException(e.getMessage(), e) {
      };
    }
  }

}
