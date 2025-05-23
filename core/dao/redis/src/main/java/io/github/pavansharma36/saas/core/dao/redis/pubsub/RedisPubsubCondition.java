package io.github.pavansharma36.saas.core.dao.redis.pubsub;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.core.common.utils.CoreConstants;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class RedisPubsubCondition implements Condition {
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    return "redis".equals(Config.get(CoreConstants.PUBSUB_IMPL_CONF, null));
  }
}
