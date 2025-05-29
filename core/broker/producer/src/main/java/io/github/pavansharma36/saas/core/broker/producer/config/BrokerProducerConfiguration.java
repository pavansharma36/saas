package io.github.pavansharma36.saas.core.broker.producer.config;

import io.github.pavansharma36.saas.core.broker.common.config.BrokerCommonConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("io.github.pavansharma36.saas.core.broker.producer")
@Import(BrokerCommonConfiguration.class)
public class BrokerProducerConfiguration {
}
