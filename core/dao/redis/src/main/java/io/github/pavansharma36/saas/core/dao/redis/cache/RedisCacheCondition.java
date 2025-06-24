package io.github.pavansharma36.saas.core.dao.redis.cache;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.core.common.utils.CoreConstants;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class RedisCacheCondition implements Condition {
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    return "redis".equals(Config.get(CoreConstants.CACHE_IMPL_CONF, null));
  }
}
