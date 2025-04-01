package io.github.pavansharma36.core.common.id.utils;

import io.github.pavansharma36.core.common.id.IdGenerator;
import io.github.pavansharma36.core.common.id.builder.IdGeneratorBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class IdGeneratorUtils {

  private static final IdGenerator RANDOM_32;

  static {
    RANDOM_32 = IdGeneratorBuilder.advanced()
        .appendSchemaIdToken(8)
        .appendTimestamp()
        .appendRandom(16)
        .build();
  }

  public static IdGenerator random32() {
    return RANDOM_32;
  }

}
