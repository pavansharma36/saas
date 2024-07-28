package io.github.pavansharma36.saas.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Enums {

  public enum AppType implements Named {
    WEB, WORKER;

    @JsonCreator
    public static AppType fromName(String name) {
      return AppType.valueOf(name.toUpperCase());
    }

    @Override
    @JsonValue
    public String getName() {
      return name();
    }
  }

}
