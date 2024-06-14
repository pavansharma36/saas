package io.github.pavansharma36.saas.galaxy.server.servlet;

import io.github.pavansharma36.core.common.config.provider.ConfigProviders;
import io.github.pavansharma36.core.common.config.provider.PropertiesFileConfigProvider;
import io.github.pavansharma36.saas.core.server.listener.StartupListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class GalaxyServerStartupListener extends StartupListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    ConfigProviders.registerConfigProvider(new PropertiesFileConfigProvider("conf/galaxy.properties", 100));
    super.contextInitialized(event);
  }
}
