package io.github.pavansharma36.core.common.pubsub.subscriber;

import io.github.pavansharma36.core.common.pubsub.payload.Payload;
import io.github.pavansharma36.core.common.utils.CoreConstants;
import io.github.pavansharma36.saas.utils.Utils;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SubscriptionManager {

  private final Subscriber subscriber;
  private final Map<String, SubscriptionHandler> handlerMap;

  public SubscriptionManager(Subscriber subscriber, List<SubscriptionHandler> handlers) {
    this.subscriber = subscriber;
    handlerMap = new HashMap<>();
    handlers.forEach(h -> {
      handlerMap.put(h.eventType(), h);
    });
  }

  private Consumer<byte[]> getDefaultHandler() {
    return (byte[] data) -> {
      try {
        Payload payload = Utils.deserialize(data, Payload.class);
        if (handlerMap.containsKey(payload.getEventType())) {
          handlerMap.get(payload.getEventType()).handle(payload.getData());
        } else {
          log.warn("No handler found for eventType: {}, ignoring event {}", payload.getEventType(),
              payload);
        }
      } catch (Throwable e) {
        log.error("Error while handling event : {}", e.getMessage(), e);
      }
    };
  }

  @PostConstruct
  public void subscribe() {
    subscriber.subscribe(getDefaultHandler());
    subscriber.subscribe(CoreConstants.APP_NAME, getDefaultHandler());
    subscriber.subscribe(CoreConstants.APP_TYPE, getDefaultHandler());
    subscriber.subscribe(CoreConstants.APP_NAME, CoreConstants.APP_TYPE, getDefaultHandler());
  }

}
