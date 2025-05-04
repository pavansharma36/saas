package io.github.pavansharma36.saas.core.dao.redis.pubsub;

import io.github.pavansharma36.core.common.pubsub.payload.Payload;
import io.github.pavansharma36.core.common.pubsub.publisher.Publisher;
import io.github.pavansharma36.saas.utils.Utils;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Slf4j
@RequiredArgsConstructor
public class RedisPublisher implements Publisher {

  private final RedisConnectionFactory connectionFactory;

  @Override
  public void publish(String channel, Payload payload) {
    try (RedisConnection redisConnection = connectionFactory.getConnection()) {
      redisConnection.publish(channel.getBytes(StandardCharsets.UTF_8),
          Utils.serialize(payload));
    }
  }

}
