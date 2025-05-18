package io.github.pavansharma36.saas.core.broker.consumer;


import io.github.pavansharma36.saas.core.broker.common.MessagePriority;
import io.github.pavansharma36.saas.core.broker.common.Queue;
import java.util.Optional;
import org.springframework.context.ApplicationContext;

public interface ConsumerTemplate {

  Optional<Byte[]> getMessage(Queue queueType, MessagePriority priority);

  void onStart(ApplicationContext applicationContext);

  String type();

}
