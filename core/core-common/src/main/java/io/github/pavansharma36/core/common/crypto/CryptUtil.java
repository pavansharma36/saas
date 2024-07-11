package io.github.pavansharma36.core.common.crypto;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import io.github.pavansharma36.saas.utils.resource.ResourceUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class CryptUtil {

  private static final String DEFAULT_KEYSTORE_FILE = "conf/keystore.p12";
  private static final String DEFAULT_KEYSTORE_PASSWORD = "password";
  private static final String KEYSTORE_TYPE = "PKCS12";

  private static final CryptKeys keys;
  private static final CryptManager manager;

  static {
    String keystoreFile = Config.get("KEYSTORE_FILE", DEFAULT_KEYSTORE_FILE);
    File file = ResourceUtils.findFileOrThrow(keystoreFile, 5);
    String keystorePassword = Config.get("KEYSTORE_PASSWORD", DEFAULT_KEYSTORE_PASSWORD);
    try (InputStream in = new FileInputStream(file)) {
      keys = new CryptKeys(in, keystorePassword, KEYSTORE_TYPE);
      manager = new CryptManager(keys);
    } catch (IOException e) {
      throw new ServerRuntimeException(e);
    }
  }

  public static SafeTuple encrypt(String value) {
    return encrypt(value, KeyType.DEFAULT);
  }

  public static SafeTuple encrypt(String value, KeyType keyType) {
    return manager.encrypt(keyType, value.getBytes(StandardCharsets.UTF_8));
  }

  public static String decrypt(KeyType keyType, String value) {
    SafeTuple s = SafeTuple.parse(keys.getLatestKey(keyType).getAlias(), value);
    return decrypt(s.getKey(), s.getEncryptedValue());
  }

  public static String decrypt(String keyAlias, byte[] value) {
    return new String(manager.decrypt(keyAlias, value), StandardCharsets.UTF_8);
  }

  public static String decryptEncoded(String encodedValue) {
    SafeTuple safeTuple = SafeTuple.parse(encodedValue);
    return decrypt(safeTuple.getKey(), safeTuple.getEncryptedValue());
  }

}
