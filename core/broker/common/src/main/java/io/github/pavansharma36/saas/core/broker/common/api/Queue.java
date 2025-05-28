package io.github.pavansharma36.saas.core.broker.common.api;

import io.github.pavansharma36.saas.utils.Named;
import java.util.List;

public interface Queue extends Named {
  String type();

  List<MessagePriority> supportedPriorities();

  List<DelayedQueue> supportedDelayedQueues();

  default String formatQueueName(MessagePriority priority) {
    return getName() + priority.queueNameSuffix();
  }
}
