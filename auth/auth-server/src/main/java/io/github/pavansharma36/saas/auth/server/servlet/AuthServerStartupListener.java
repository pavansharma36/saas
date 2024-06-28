package io.github.pavansharma36.saas.auth.server.servlet;

import io.github.pavansharma36.core.common.config.provider.ConfigProviders;
import io.github.pavansharma36.core.common.config.provider.PropertiesFileConfigProvider;
import io.github.pavansharma36.saas.core.server.listener.StartupListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class AuthServerStartupListener extends StartupListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    ConfigProviders.registerConfigProvider(
        new PropertiesFileConfigProvider("conf/auth.properties", 100));
    super.contextInitialized(event);
  }
}
