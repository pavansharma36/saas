package io.github.pavansharma36.saas.utils;

import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Utils {
  public static String randomRequestId() {
    return RandomStringUtils.randomAlphanumeric(12);
  }

  public static void sleep(long millis) throws InterruptedException {
    Thread.sleep(millis);
  }

  public static void sleepQuietly(long millis) {
    try {
      sleep(millis);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ServerRuntimeException(e) {
      };
    }
  }

  public static void sleepSeconds(int seconds) throws InterruptedException {
    Thread.sleep(seconds * 1000L);
  }

  public static void sleepSecondsQuietly(int seconds) {
    try {
      sleepSeconds(seconds);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ServerRuntimeException(e);
    }
  }
}
