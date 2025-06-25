package io.github.pavansharma36.saas.core.broker.common;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageSerializablePayload;
import io.github.pavansharma36.saas.utils.Utils;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import io.github.pavansharma36.saas.utils.poll.DelayedLogEmitter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class BrokerUtils {

  public static byte[] serialize(MessageSerializablePayload payload) {
    return Utils.serialize(payload);
  }

  public static MessageSerializablePayload deserialize(byte[] data) {
    return Utils.deserialize(data, MessageSerializablePayload.class);
  }

  public static List<MessagePriority> sortDesc(List<MessagePriority> priorities) {
    return priorities.stream().sorted((a, b) -> b.priority() - a.priority())
        .toList();
  }

  public static boolean isQueueBlocked(Queue queue, MessagePriority priority,
                                       DelayedLogEmitter log) {
    if (Config.getBoolean("queue.block.all", false)) {
      log.info("All queues are blocked, not polling for any message");
      return true;
    } else if (Config.getBoolean(String.format("%s.queue.block.all", queue.getName()), false)) {
      log.info("Queue is blocked, not polling for any message : {}", queue.getName());
      return true;
    }
    if (priority != null) {
      String queueName = queue.formatQueueName(priority);
      if (Config.getBoolean(
          String.format("%s.priority.queue.block.all", queueName),
          false)) {
        log.info("Queue with priority blocked, not polling for any message : {}", queueName);
        return true;
      }
    }

    return false;
  }

  public static boolean isMessageTypeBlocked(String messageType, Logger log) {
    if (Config.getBoolean(String.format("%s.message.block.all", messageType), false)) {
      log.info("MessageType is blocked, not processing now");
      return true;
    }
    return false;
  }

  public static DelayedQueue nextDelayedQueue(Queue queue,
                                              DelayedQueue currentDelayedQueue) {
    List<DelayedQueue> queues =
        queue.supportedDelayedQueues().stream().sorted(Comparator.comparing(DelayedQueue::getDelay))
            .toList();

    Iterator<DelayedQueue> it = queues.iterator();
    while (it.hasNext()) {
      if (currentDelayedQueue == null) {
        return it.next();
      }
      DelayedQueue dq = it.next();
      if (dq == currentDelayedQueue) {
        if (it.hasNext()) {
          return it.next();
        } else {
          return dq;
        }
      }
    }
    throw new ServerRuntimeException(
        String.format("Delayed queues are required: %s", queue.getName()));

  }

}
