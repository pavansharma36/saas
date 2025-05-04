package io.github.pavansharma36.saas.core.dao.redis.pubsub;

import io.github.pavansharma36.core.common.pubsub.publisher.Publisher;
import io.github.pavansharma36.core.common.pubsub.subscriber.Subscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Conditional(RedisPubsubCondition.class)
@Configuration
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
