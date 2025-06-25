package io.github.pavansharma36.saas.galaxy.worker;

import io.github.pavansharma36.saas.core.broker.consumer.ConsumerBootstrapProcessor;
import io.github.pavansharma36.saas.galaxy.common.utils.GalaxyConstants;
import io.github.pavansharma36.saas.galaxy.worker.config.GalaxyWorkerConfiguration;

public class Main {

  public static void main(String[] args) {
    ConsumerBootstrapProcessor.startConsumer(GalaxyConstants.GALAXY_APP_NAME,
        GalaxyWorkerConfiguration.class, GalaxyConstants.GALAXY_QUEUE);
  }
}
