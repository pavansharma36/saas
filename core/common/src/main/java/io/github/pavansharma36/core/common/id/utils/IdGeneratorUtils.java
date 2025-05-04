package io.github.pavansharma36.core.common.id.utils;

import io.github.pavansharma36.core.common.context.providers.TenantContextProvider;
import io.github.pavansharma36.core.common.id.IdGenerator;
import io.github.pavansharma36.core.common.id.builder.IdGeneratorBuilder;
import io.github.pavansharma36.saas.core.dto.common.TenantDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class IdGeneratorUtils {

  private static final IdGenerator RANDOM_32;
  private static final IdGenerator TENANT_32;

  static {
    RANDOM_32 = IdGeneratorBuilder.advanced()
        .appendSchemaIdToken(8)
        .appendTimestamp()
        .appendRandom(16)
        .build();

    TENANT_32 = IdGeneratorBuilder.advanced()
        .appendSchemaIdToken(8)
        .appendTimestamp()
        .appendIntToken("tenantId",
            () -> TenantContextProvider.getInstance().get().map(TenantDto::getIncrementalId)
                .orElse(0))
        .appendRandom(8)
        .build();
  }

  public static IdGenerator random32() {
    return RANDOM_32;
  }

  public static IdGenerator tenant32() {
    return TENANT_32;
  }

}
