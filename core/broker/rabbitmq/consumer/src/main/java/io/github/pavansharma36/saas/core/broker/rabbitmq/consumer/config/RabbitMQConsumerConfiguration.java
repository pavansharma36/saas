package io.github.pavansharma36.saas.core.broker.rabbitmq.consumer.config;

import io.github.pavansharma36.saas.core.broker.consumer.config.BrokerConsumerConfiguration;
import io.github.pavansharma36.saas.core.broker.rabbitmq.producer.config.RabbitMQProducerConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("io.github.pavansharma36.saas.core.broker.rabbitmq.consumer")
@Import({RabbitMQProducerConfiguration.class, BrokerConsumerConfiguration.class})
public class RabbitMQConsumerConfiguration {
}
