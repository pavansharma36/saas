package io.github.pavansharma36.saas.core.common.pubsub.publisher;

import io.github.pavansharma36.saas.core.common.pubsub.payload.Payload;
import io.github.pavansharma36.saas.core.common.pubsub.utils.PubSubUtils;
import io.github.pavansharma36.saas.utils.Enums;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PublisherManager {

  private final Publisher publisher;
  private final String namespace = PubSubUtils.getNamespace();

  public void publish(Payload payload) {
    publish(namespace, payload);
  }

  public void publishToApp(Payload payload, String appName) {
    publish(PubSubUtils.getChannelName(namespace, appName), payload);
  }

  public void publishToAppType(Payload payload, Enums.AppType appType) {
    publish(PubSubUtils.getChannelName(namespace, appType), payload);
  }

  public void publishToAppAndType(Payload payload, String appName, Enums.AppType appType) {
    publish(PubSubUtils.getChannelName(namespace, appName, appType), payload);
  }

  private void publish(String channel, Payload payload) {
    try {
      publisher.publish(channel, payload);
    } catch (Exception e) {
      log.error("Error while publishing message {}:{} : {}", channel, payload.getEventType(),
          e.getMessage(), e);
    }
  }

}
