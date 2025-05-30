package io.github.pavansharma36.saas.galaxy.worker.config;

import io.github.pavansharma36.saas.core.broker.rabbitmq.consumer.config.RabbitMQConsumerConfiguration;
import io.github.pavansharma36.saas.galaxy.common.config.GalaxyCommonConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("io.github.pavansharma36.saas.galaxy.worker")
@Import({GalaxyCommonConfiguration.class, RabbitMQConsumerConfiguration.class})
public class GalaxyWorkerConfiguration {
}
