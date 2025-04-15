package io.github.pavansharma36.saas.core.pubsub.redis;

import io.github.pavansharma36.core.common.pubsub.Publisher;
import io.github.pavansharma36.core.common.pubsub.subscriber.Subscriber;
import io.github.pavansharma36.core.common.pubsub.utils.PubSubUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisPubSubConfig {

  @Bean
  public RedisConnectionFactory connectionFactory() {
    return new JedisConnectionFactory(new JedisPoolConfig());
  }

  @Bean
  public Publisher publisher() {
    return new RedisPublisher(PubSubUtils.getNamespace(), connectionFactory());
  }

  @Bean
  public Subscriber subscriber() {
    return new RedisSubscriber(PubSubUtils.getNamespace(), connectionFactory());
  }

}
