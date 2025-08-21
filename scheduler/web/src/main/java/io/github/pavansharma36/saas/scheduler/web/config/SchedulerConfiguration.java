package io.github.pavansharma36.saas.scheduler.web.config;

import io.github.pavansharma36.saas.scheduler.common.config.SchedulerCommonConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SchedulerCommonConfiguration.class)
@ComponentScan("io.github.pavansharma36.saas.scheduler.web")
public class SchedulerConfiguration {
}
