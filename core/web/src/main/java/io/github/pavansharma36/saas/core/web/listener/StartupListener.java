package io.github.pavansharma36.saas.core.web.listener;

import io.github.pavansharma36.core.common.listener.AppLoaderListener;
import jakarta.servlet.ServletContextEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    log.info("[Context Initialized] {}:{}", event.getServletContext(), context);
    Map<String, AppLoaderListener> listeners =
        context.getBeansOfType(AppLoaderListener.class);
    listeners.values().forEach(l -> {
      log.info("Invoking on start on {}", l.getClass());
      l.onStart(context);
    });
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    log.info("[Context Destroyed] {}", event.getServletContext().getContextPath());

    WebApplicationContext context =
        WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
    log.info("[Context Destroyed] {}:{}", event.getServletContext(), context);
    Map<String, AppLoaderListener> listeners =
        context.getBeansOfType(AppLoaderListener.class);
    List<AppLoaderListener> lv = new LinkedList<>(listeners.values());
    Collections.reverse(lv);
    lv.forEach(l -> {
      log.info("Invoking on stop on {}", l.getClass());
      l.onStop(context);
    });

    super.contextDestroyed(event);
  }

}
