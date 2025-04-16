package io.github.pavansharma36.core.common.listener;

import io.github.pavansharma36.core.common.pubsub.subscriber.SubscriptionManager;
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
    // reload log4j config with all props.
    Configurator.reconfigure();

    subscriptionManager.subscribe();
  }

  @Override
  public void onStop(ApplicationContext applicationContext) {

  }
}
