package io.github.pavansharma36.saas.galaxy.web.servlet;

import io.github.pavansharma36.core.common.config.provider.PropertiesFileConfigProvider;
import io.github.pavansharma36.saas.core.web.listener.StartupListener;
import io.github.pavansharma36.saas.utils.Enums;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class GalaxyStartupListener extends StartupListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    PropertiesFileConfigProvider.registerPropertiesFileProviders("galaxy",
        Enums.AppType.WEB);
    super.contextInitialized(event);
  }
}
