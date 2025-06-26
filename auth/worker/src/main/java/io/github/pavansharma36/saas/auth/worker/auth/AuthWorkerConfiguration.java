package io.github.pavansharma36.saas.auth.worker.auth;

import io.github.pavansharma36.saas.auth.common.config.AuthCommonConfiguration;
import io.github.pavansharma36.saas.core.broker.rabbitmq.consumer.config.RabbitMQConsumerConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("io.github.pavansharma36.saas.auth.worker")
@Import({AuthCommonConfiguration.class, RabbitMQConsumerConfiguration.class})
public class AuthWorkerConfiguration {
}
