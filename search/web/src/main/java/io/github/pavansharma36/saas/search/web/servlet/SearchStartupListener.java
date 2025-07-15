package io.github.pavansharma36.saas.search.web.servlet;

import io.github.pavansharma36.core.common.utils.CoreUtils;
import io.github.pavansharma36.saas.core.web.listener.StartupListener;
import io.github.pavansharma36.saas.search.common.utils.SearchConstants;
import io.github.pavansharma36.saas.utils.Enums;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class SearchStartupListener extends StartupListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    CoreUtils.initApp(SearchConstants.APP_NAME, Enums.AppType.WEB);
    super.contextInitialized(event);
  }

}
