package io.github.pavansharma36.core.common.pubsub.config;

import io.github.pavansharma36.core.common.pubsub.subscriber.Subscriber;
import io.github.pavansharma36.core.common.pubsub.subscriber.SubscriptionHandler;
import io.github.pavansharma36.core.common.pubsub.subscriber.SubscriptionManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PubSubConfig {

  private final Subscriber subscriber;

  @Bean
  public SubscriptionManager manager(Subscriber subscriber, List<SubscriptionHandler> handlers) {
    return new SubscriptionManager(subscriber, handlers);
  }

}
