package io.github.pavansharma36.saas.scheduler.common;

import io.github.pavansharma36.saas.core.common.config.Config;
import io.github.pavansharma36.saas.core.common.utils.ShutdownHooks;
import java.util.Map;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

@Slf4j
public class SchedulerFactory {

  private static final Object LOCK = new Object();
  private static final String CONF_PREFIX = "org.quartz.";

  private static volatile Scheduler scheduler;

  public static Scheduler getScheduler() throws SchedulerException {
    if (scheduler == null) {
      synchronized (LOCK) {
        if (scheduler == null) {
          Map<String, String> quartzConfs = Config.getAllByPrefix(CONF_PREFIX);

          Properties properties = new Properties();
          properties.putAll(quartzConfs);
          org.quartz.SchedulerFactory factory = new org.quartz.impl.StdSchedulerFactory(properties);
          scheduler = factory.getScheduler();

          ShutdownHooks.registerShutdownHook(Integer.MAX_VALUE, "QuartzSchedulerShutdown", () -> {
            try {
              scheduler.shutdown();
            } catch (SchedulerException e) {
              log.error("Error while shutting down scheduler {}", e.getMessage(), e);
            }
          });
        }
      }
    }
    return scheduler;
  }

}
