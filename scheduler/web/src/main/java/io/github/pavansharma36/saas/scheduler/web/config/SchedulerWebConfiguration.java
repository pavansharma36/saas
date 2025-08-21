package io.github.pavansharma36.saas.scheduler.web.config;

import io.github.pavansharma36.saas.core.web.config.AbstractWebConfig;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"io.github.pavansharma36.saas.auth.web.api"})
public class SchedulerWebConfiguration extends AbstractWebConfig {
}
