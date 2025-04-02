package io.github.pavansharma36.core.common.logging;

import io.github.pavansharma36.core.common.config.Config;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;

/**
 * A log4j2 plugin which allows us to substitute app config in log4j config file.
 * Eg <b>logLevel=${config:log.level:-INFO}</b>
 * <p>
 * plugin registered using maven log4j plugin register in pom.xml
 * </p>
 */
@Plugin(name = "config", category = StrLookup.CATEGORY)
public class Log4J2ConfigLookup implements StrLookup {
  @Override
  public String lookup(String key) {
    return Config.get(key, null);
  }

  @Override
  public String lookup(LogEvent event, String key) {
    return Config.get(key, null);
  }
}
