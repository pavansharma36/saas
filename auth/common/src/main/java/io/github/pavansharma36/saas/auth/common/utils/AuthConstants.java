package io.github.pavansharma36.saas.auth.common.utils;

import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.exchange.RabbitExchange;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.queue.RabbitQueueImpl;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AuthConstants {

  public static final String APP_NAME = "auth";

  public static final Queue AUTH_QUEUE = new RabbitQueueImpl("auth", RabbitExchange.DEFAULT,
      List.of(MessagePriority.NORMAL), List.of(DelayedQueue.ONE_MINUTE));


}
