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
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ConfigProviders {

  private static final List<ConfigProvider> PROVIDERS = new LinkedList<>();
  private static final StringEncryptor ENCRYPTOR;

  static {
    registerConfigProvider(new SystemConfigProvider());
    registerConfigProvider(new EnvConfigProvider());
    registerConfigProvider(
        new PropertiesFileConfigProvider("conf/common.properties", Integer.MAX_VALUE - 20));

    ENCRYPTOR = new StringEncryptor() {
      @Override
      public String encrypt(String s) {
        return CryptUtil.encrypt(s).encoded();
      }

      @Override
      public String decrypt(String s) {
        return CryptUtil.decryptEncoded(s);
      }
    };
  }

  public static synchronized void registerConfigProvider(ConfigProvider provider) {
    PROVIDERS.add(provider);
    PROVIDERS.sort(Comparator.comparing(ConfigProvider::order));
  }

  public static String getConfig(String key) {
    for (ConfigProvider provider : PROVIDERS) {
      String a = provider.getConfig(key);
      if (a != null) {
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

  public static String decryptIfEncrypted(String value) {
    if (!PropertyValueEncryptionUtils.isEncryptedValue(value)) {
      return value;
    }
    return PropertyValueEncryptionUtils.decrypt(value, ENCRYPTOR);
  }

}
