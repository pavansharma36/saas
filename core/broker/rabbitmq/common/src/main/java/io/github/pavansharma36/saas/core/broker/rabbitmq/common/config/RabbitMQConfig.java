package io.github.pavansharma36.saas.core.broker.rabbitmq.common.config;

import com.rabbitmq.client.ConnectionFactory;
import io.github.pavansharma36.core.common.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Bean
  public ConnectionFactory connectionFactory() {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(Config.get("rabbitmq.host"));
    factory.setPort(Config.getInt("rabbitmq.port", 5672)); // Default port
    factory.setUsername(Config.get("rabbitmq.username")); // Default user
    factory.setPassword(Config.get("rabbitmq.password")); // Default password
    factory.setVirtualHost(Config.get("rabbitmq.vhost", "/")); //
    return factory;
  }

}
