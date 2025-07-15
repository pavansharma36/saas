package io.github.pavansharma36.saas.core.dao.redis.mutex;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.core.common.utils.CoreConstants;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class RedisLockCondition implements Condition {
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    return "redis".equals(Config.get(CoreConstants.MUTEX_IMPL_CONF, null));
  }
}
