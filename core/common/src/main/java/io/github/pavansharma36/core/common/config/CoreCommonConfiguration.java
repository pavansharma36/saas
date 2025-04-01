package io.github.pavansharma36.core.common.config;

import io.github.pavansharma36.core.common.cache.InmemoryCaches;
import io.github.pavansharma36.core.common.factory.ExecutorFactory;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("io.github.pavansharma36.core.common")
public class CoreCommonConfiguration {

  @Bean
  public ScheduledExecutorService scheduledExecutorService() {
    ScheduledExecutorService scheduledExecutorService = ExecutorFactory.scheduledExecutorService();
    scheduledExecutorService.scheduleWithFixedDelay(InmemoryCaches::scheduledClear, 1L, 1L,
        TimeUnit.MINUTES);
    return scheduledExecutorService;
  }

}
