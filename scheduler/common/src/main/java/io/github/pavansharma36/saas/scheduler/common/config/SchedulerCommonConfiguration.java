package io.github.pavansharma36.saas.scheduler.common.config;

import io.github.pavansharma36.saas.core.common.config.CoreConfiguration;
import io.github.pavansharma36.saas.core.dao.redis.config.RedisConfig;
import io.github.pavansharma36.saas.galaxy.client.config.GalaxyClientConfig;
import io.github.pavansharma36.saas.scheduler.common.SchedulerFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CoreConfiguration.class, GalaxyClientConfig.class, RedisConfig.class})
@ComponentScan("io.github.pavansharma36.saas.scheduler.common")
public class SchedulerCommonConfiguration {

  @Bean
  public Scheduler scheduler() throws SchedulerException {
    return SchedulerFactory.getScheduler();
  }

}
