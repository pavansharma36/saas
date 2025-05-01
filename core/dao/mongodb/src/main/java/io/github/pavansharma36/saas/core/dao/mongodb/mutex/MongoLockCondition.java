package io.github.pavansharma36.saas.core.dao.mongodb.mutex;

import io.github.pavansharma36.core.common.config.Config;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MongoLockCondition implements Condition {
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    return "mongodb".equals(Config.get("mutex.impl", null));
  }
}
