package io.github.pavansharma36.saas.core.pubsub.redis;

import io.github.pavansharma36.core.common.pubsub.publisher.Publisher;
import io.github.pavansharma36.core.common.pubsub.subscriber.Subscriber;
import io.github.pavansharma36.saas.core.dao.redis.config.RedisConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;


@Configuration
@Import(RedisConfig.class)
@RequiredArgsConstructor
public class RedisPubSubConfig {

  private final RedisConnectionFactory redisConnectionFactory;

  @Bean
  public Publisher publisher() {
    return new RedisPublisher(redisConnectionFactory);
  }

  @Bean
  public Subscriber subscriber() {
    return new RedisSubscriber(redisConnectionFactory);
  }

}
