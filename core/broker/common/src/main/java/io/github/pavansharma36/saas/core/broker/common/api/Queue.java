package io.github.pavansharma36.saas.core.broker.common.api;

import io.github.pavansharma36.saas.utils.Named;
import java.util.List;

public interface Queue extends Named {
  String type();

  List<MessagePriority> supportedPriorities();

  List<DelayedQueue> supportedDelayedQueues();

  default String formatSeparator() {
    return "-";
  }

  default String formatQueueName(MessagePriority priority) {
    return String.format("%s%s%s", getName(), formatSeparator(), priority.queueNameSuffix());
  }

  default String formatQueueName(DelayedQueue delayedQueue) {
    return String.format("%s%s%s", getName(), formatSeparator(), delayedQueue.getQueueNameSuffix());
  }
}
