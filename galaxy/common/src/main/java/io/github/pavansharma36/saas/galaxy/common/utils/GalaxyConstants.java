package io.github.pavansharma36.saas.galaxy.common.utils;

import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.exchange.RabbitExchange;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.queue.RabbitQueueImpl;
import java.util.List;

public class GalaxyConstants {

  public static final String GALAXY_APP_NAME = "galaxy";
  public static final String CONFIG_CLASSIFIER_GLOBAL = "global";
  public static final String CONFIG_CLASSIFIER_APPTYPE = "app.type";
  public static final String CONFIG_CLASSIFIER_APPNAME = "app.name";
  public static final String CONFIG_CLASSIFIER_APP_NAME_TYPE =
      String.format("%s-%s", CONFIG_CLASSIFIER_APPNAME, CONFIG_CLASSIFIER_APPTYPE);

  public static final Queue GALAXY_QUEUE =
      new RabbitQueueImpl("galaxy", RabbitExchange.DEFAULT,
          List.of(MessagePriority.NORMAL, MessagePriority.LOW, MessagePriority.HIGH),
          List.of(DelayedQueue.ONE_MINUTE));

}
