package io.github.pavansharma36.saas.core.broker.consumer.api;

import org.springframework.context.ApplicationContext;

public interface ConsumerLifeCycle {

  default void preStart(ApplicationContext context) {

  }

  default void postStart(ApplicationContext context) {

  }

}
