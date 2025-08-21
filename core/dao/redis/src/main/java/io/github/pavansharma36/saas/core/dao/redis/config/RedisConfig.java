package io.github.pavansharma36.saas.core.dao.redis.config;

import io.github.pavansharma36.saas.core.common.config.Config;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisConfig {

  @Bean
  @Lazy
  public RedisConnectionFactory redisConnectionFactory() {
    String host = Config.get("redis.host");
    int port = Config.getInt("redis.port");

    RedisStandaloneConfiguration config =
        new RedisStandaloneConfiguration(host, port);

    Optional.ofNullable(Config.get("redis.username", null)).ifPresent(config::setUsername);
    Optional.ofNullable(Config.get("redis.password", null)).ifPresent(config::setPassword);
    config.setDatabase(Config.getInt("redis.database", 0));

    JedisClientConfiguration.JedisClientConfigurationBuilder clientConfigurationBuilder =
        JedisClientConfiguration.builder();

    return new JedisConnectionFactory(config, clientConfigurationBuilder.build());
  }

}
