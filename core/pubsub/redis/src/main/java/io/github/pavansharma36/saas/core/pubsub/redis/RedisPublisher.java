package io.github.pavansharma36.saas.core.pubsub.redis;

import io.github.pavansharma36.core.pubsub.common.Publisher;
import io.github.pavansharma36.core.pubsub.common.payload.Payload;
import io.github.pavansharma36.core.pubsub.common.utils.PubSubUtils;
import io.github.pavansharma36.saas.utils.Enums;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Slf4j
@RequiredArgsConstructor
public class RedisPublisher implements Publisher {

  private final String namespace;
  private final RedisConnectionFactory connectionFactory;

  @Override
  public void publish(Payload payload) {
    publishPayloadToChannel(payload, namespace);
  }

  @Override
  public void publishToApp(Payload payload, String appName) {
    publishPayloadToChannel(payload, PubSubUtils.getChannelName(namespace, appName));
  }

  @Override
  public void publishToAppType(Payload payload, Enums.AppType appType) {
    publishPayloadToChannel(payload, PubSubUtils.getChannelName(namespace, appType));
  }

  @Override
  public void publishToAppAndType(Payload payload, String appName, Enums.AppType appType) {
    publishPayloadToChannel(payload, PubSubUtils.getChannelName(namespace, appName, appType));
  }

  private void publishPayloadToChannel(Payload payload, String channel) {
    connectionFactory.getConnection()
        .publish(channel.getBytes(StandardCharsets.UTF_8),
            JsonUtils.toJson(payload).getBytes(StandardCharsets.UTF_8));
  }
}
