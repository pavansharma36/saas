package io.github.pavansharma36.saas.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Enums {

  public enum AppType implements Named {
    WEB, WORKER;

    public static AppType fromName(String name) {
      return AppType.valueOf(name.toUpperCase());
    }
  }

}
