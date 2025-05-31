package io.github.pavansharma36.core.common.listener;

import io.github.pavansharma36.core.common.config.provider.ConfigProviders;
import io.github.pavansharma36.core.common.config.provider.GalaxyConfigProvider;
import io.github.pavansharma36.core.common.pubsub.subscriber.SubscriptionManager;
import io.github.pavansharma36.core.common.service.ConfigService;
import io.github.pavansharma36.core.common.utils.CoreConstants;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(100)
@RequiredArgsConstructor
public class CoreAppLoaderListener implements AppLoaderListener {

  private final SubscriptionManager subscriptionManager;

  @Override
  public void onStart(ApplicationContext applicationContext) {
    ConfigService configService = applicationContext.getBean(ConfigService.class);
    ConfigProviders.registerConfigProvider(
        new GalaxyConfigProvider(configService, CoreConstants.APP_NAME, CoreConstants.APP_TYPE));

    // reload log4j config with all props.
    Configurator.reconfigure();

    subscriptionManager.subscribe();
  }

  @Override
  public void onStop(ApplicationContext applicationContext) {

  }
}
