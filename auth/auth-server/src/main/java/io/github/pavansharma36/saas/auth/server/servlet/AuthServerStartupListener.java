package io.github.pavansharma36.saas.auth.server.servlet;

import io.github.pavansharma36.core.common.config.provider.ConfigProviders;
import io.github.pavansharma36.core.common.config.provider.PropertiesFileConfigProvider;
import io.github.pavansharma36.saas.auth.common.utils.AuthConstants;
import io.github.pavansharma36.saas.core.server.listener.StartupListener;
import io.github.pavansharma36.saas.galaxy.client.config.GalaxyConfigProvider;
import io.github.pavansharma36.saas.utils.Enums;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class AuthServerStartupListener extends StartupListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    ConfigProviders.registerConfigProvider(
        new PropertiesFileConfigProvider("conf/app_type/web.properties", 20000));
    ConfigProviders.registerConfigProvider(
        new PropertiesFileConfigProvider("conf/app_name/auth.properties", 10000));
    ConfigProviders.registerConfigProvider(
        new GalaxyConfigProvider(AuthConstants.APP_NAME, Enums.AppType.WEB));
    super.contextInitialized(event);
  }
}
