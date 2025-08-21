package io.github.pavansharma36.saas.core.broker.rabbitmq.common.config;

import com.rabbitmq.client.ConnectionFactory;
import io.github.pavansharma36.saas.core.common.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class RabbitMQCommonConfiguration {

  @Bean
  @Lazy
  public ConnectionFactory rabbitMQConnectionFactory() {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(Config.get("rabbitmq.host"));
    factory.setPort(Config.getInt("rabbitmq.port", 5672)); // Default port
    factory.setUsername(Config.get("rabbitmq.username"));
    factory.setPassword(Config.get("rabbitmq.password"));
    factory.setVirtualHost(Config.get("rabbitmq.vhost", "/")); // Default vhost
    factory.setAutomaticRecoveryEnabled(true);
    return factory;
  }

}
