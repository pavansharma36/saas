package io.github.pavansharma36.saas.core.server.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Slf4j
public class StartupListener extends ContextLoaderListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    super.contextInitialized(event);
    WebApplicationContext context =
        WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
    log.info("[Context Initialized] {}", context);
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    log.info("[Context Destroyed] {}", event.getServletContext().getContextPath());
    super.contextDestroyed(event);
  }
}
