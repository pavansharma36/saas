package io.github.pavansharma36.saas.scheduler.common;

import io.github.pavansharma36.core.common.config.Config;
import java.util.Map;
import java.util.Properties;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

public class SchedulerFactory {

  private static final String CONF_PREFIX = "quartz.conf.";

  public static Scheduler getScheduler() throws SchedulerException {
    Map<String, String> quartzConfs = Config.getAllByPrefix(CONF_PREFIX);

    Properties properties = new Properties();
    for (Map.Entry<String, String> entry : quartzConfs.entrySet()) {
      properties.put(entry.getKey().replace(CONF_PREFIX, ""), entry.getValue());
    }
    org.quartz.SchedulerFactory factory = new org.quartz.impl.StdSchedulerFactory(properties);
    return factory.getScheduler();
  }

}
