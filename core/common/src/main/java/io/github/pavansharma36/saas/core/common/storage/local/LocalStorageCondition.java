package io.github.pavansharma36.saas.core.common.storage.local;

import io.github.pavansharma36.saas.core.common.config.Config;
import io.github.pavansharma36.saas.core.common.storage.StorageService;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LocalStorageCondition implements Condition {
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    return "local".equalsIgnoreCase(Config.get(StorageService.IMPL_CONF));
  }
}
