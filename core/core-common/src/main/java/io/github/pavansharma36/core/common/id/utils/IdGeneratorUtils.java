package io.github.pavansharma36.core.common.id.utils;

import io.github.pavansharma36.core.common.id.IdGenerator;
import io.github.pavansharma36.core.common.id.builder.IdGeneratorBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class IdGeneratorUtils {

  public static IdGenerator random32() {
    return IdGeneratorBuilder.advanced()
        .appendSchemaIdToken(8)
        .appendTimestamp()
        .appendRandom(16)
        .build();
  }

}
