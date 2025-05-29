package io.github.pavansharma36.saas.core.broker.consumer.config;

import io.github.pavansharma36.saas.core.broker.producer.config.BrokerProducerConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("io.github.pavansharma36.saas.core.broker.consumer")
@Import(BrokerProducerConfiguration.class)
public class BrokerConsumerConfiguration {
}
