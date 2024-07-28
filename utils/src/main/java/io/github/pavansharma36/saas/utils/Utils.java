package io.github.pavansharma36.saas.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Utils {
  public static String randomRequestId() {
    return RandomStringUtils.randomAlphanumeric(12);
  }
}
