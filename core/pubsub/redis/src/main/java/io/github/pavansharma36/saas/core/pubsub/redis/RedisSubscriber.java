package io.github.pavansharma36.saas.core.pubsub.redis;

import io.github.pavansharma36.core.common.pubsub.subscriber.Subscriber;
import io.github.pavansharma36.core.common.pubsub.utils.PubSubUtils;
import io.github.pavansharma36.saas.utils.Enums;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Slf4j
@RequiredArgsConstructor
public class RedisSubscriber implements Subscriber {
  private final String namespace;
  private final RedisConnectionFactory connectionFactory;

  private MessageListener getListener(Consumer<byte[]> consumer) {
    return ((message, pattern) -> {
      consumer.accept(message.getBody());
    });
  }

  @Override
  public void subscribe(Consumer<byte[]> consumer) {
    connectionFactory.getConnection()
        .subscribe(getListener(consumer), namespace.getBytes(
            StandardCharsets.UTF_8));
  }

  @Override
  public void subscribe(String appName, Consumer<byte[]> consumer) {
    connectionFactory.getConnection()
        .subscribe(getListener(consumer), PubSubUtils.getChannelName(namespace, appName).getBytes(
            StandardCharsets.UTF_8));
  }

  @Override
  public void subscribe(Enums.AppType appType, Consumer<byte[]> consumer) {
    connectionFactory.getConnection().subscribe(getListener(consumer),
        PubSubUtils.getChannelName(namespace, appType).getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public void subscribe(String appName, Enums.AppType appType, Consumer<byte[]> consumer) {
    connectionFactory.getConnection().subscribe(getListener(consumer),
        PubSubUtils.getChannelName(namespace, appName, appType).getBytes(StandardCharsets.UTF_8));
  }
}
