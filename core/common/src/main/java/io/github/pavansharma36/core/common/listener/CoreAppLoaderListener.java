package io.github.pavansharma36.core.common.listener;

import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(100)
public class CoreAppLoaderListener implements AppLoaderListener {
  
  @Override
  public void onStart(ApplicationContext applicationContext) {
    // reload log4j config with all props.
    Configurator.reconfigure();
  }

  @Override
  public void onStop(ApplicationContext applicationContext) {

  }
}
