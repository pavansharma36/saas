package io.github.pavansharma36.saas.core.broker.rabbitmq.producer.config;

import io.github.pavansharma36.saas.core.broker.producer.config.BrokerProducerConfiguration;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.config.RabbitMQCommonConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("io.github.pavansharma36.saas.core.broker.rabbitmq.producer")
@Import({RabbitMQCommonConfiguration.class, BrokerProducerConfiguration.class})
public class RabbitMQProducerConfiguration {
}
