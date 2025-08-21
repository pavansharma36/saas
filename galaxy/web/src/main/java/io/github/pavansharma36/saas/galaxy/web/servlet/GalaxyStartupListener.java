package io.github.pavansharma36.saas.galaxy.web.servlet;

import io.github.pavansharma36.saas.core.common.utils.CoreUtils;
import io.github.pavansharma36.saas.core.web.listener.StartupListener;
import io.github.pavansharma36.saas.galaxy.common.utils.GalaxyConstants;
import io.github.pavansharma36.saas.utils.Enums;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class GalaxyStartupListener extends StartupListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    CoreUtils.initApp(GalaxyConstants.GALAXY_APP_NAME, Enums.AppType.WEB);
    super.contextInitialized(event);
  }
}
