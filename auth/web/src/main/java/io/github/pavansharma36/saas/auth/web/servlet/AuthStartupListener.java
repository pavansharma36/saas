package io.github.pavansharma36.saas.auth.web.servlet;

import io.github.pavansharma36.core.common.config.provider.ConfigProviders;
import io.github.pavansharma36.core.common.config.provider.PropertiesFileConfigProvider;
import io.github.pavansharma36.saas.auth.common.utils.AuthConstants;
import io.github.pavansharma36.saas.core.web.listener.StartupListener;
import io.github.pavansharma36.saas.galaxy.client.config.GalaxyConfigProvider;
import io.github.pavansharma36.saas.utils.Enums;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class AuthStartupListener extends StartupListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    PropertiesFileConfigProvider.registerPropertiesFileProviders(AuthConstants.APP_NAME,
        Enums.AppType.WEB);
    ConfigProviders.registerConfigProvider(
        new GalaxyConfigProvider(AuthConstants.APP_NAME, Enums.AppType.WEB));
    super.contextInitialized(event);
  }
}
