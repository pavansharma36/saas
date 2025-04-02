package io.github.pavansharma36.core.common.config.provider;

import io.github.pavansharma36.core.common.crypto.CryptUtil;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ConfigProviders {

  private static final String ENC_PREFIX = "ENC(";
  private static final String ENC_SUFFIX = ")";
  private static final int ENC_PREFIX_LENGTH = ENC_PREFIX.length();
  private static final int ENC_SUFFIX_LENGTH = ENC_SUFFIX.length();
  private static final List<ConfigProvider> PROVIDERS = new LinkedList<>();

  static {
    registerConfigProvider(new SystemConfigProvider());
    registerConfigProvider(new EnvConfigProvider());
  }

  public static synchronized void registerConfigProvider(ConfigProvider provider) {
    PROVIDERS.add(provider);
    PROVIDERS.sort(Comparator.comparing(ConfigProvider::order));
  }

  public static String getConfig(String key) {
    for (ConfigProvider provider : PROVIDERS) {
      String a = provider.getConfig(key);
      if (a != null) {
        log.info("Config {} found in {}", key, provider);
        return decryptIfEncrypted(a);
      }
    }
    return null;
  }

  public static Map<String, String> getAll() {
    Map<String, String> m = new HashMap<>();
    for (ConfigProvider provider : PROVIDERS.reversed()) {
      m.putAll(provider.getAll());
    }
    return m.entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e -> decryptIfEncrypted(e.getValue())));
  }

  private static String decryptIfEncrypted(String value) {
    if (!isEncryptedValue(value)) {
      return value;
    }
    return CryptUtil.decryptEncoded(
        value.substring(ENC_PREFIX_LENGTH, value.length() - ENC_SUFFIX_LENGTH));
  }

  private static boolean isEncryptedValue(String value) {
    if (value == null) {
      return false;
    } else {
      String trimmedValue = value.trim();
      return trimmedValue.startsWith("ENC(") && trimmedValue.endsWith(")");
    }
  }

}
