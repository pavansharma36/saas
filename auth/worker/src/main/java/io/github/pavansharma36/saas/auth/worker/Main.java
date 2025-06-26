package io.github.pavansharma36.saas.auth.worker;

import io.github.pavansharma36.saas.auth.common.utils.AuthConstants;
import io.github.pavansharma36.saas.auth.worker.auth.AuthWorkerConfiguration;
import io.github.pavansharma36.saas.core.broker.consumer.ConsumerBootstrapProcessor;

public class Main {

  public static void main(String[] args) {
    ConsumerBootstrapProcessor.startConsumer(AuthConstants.APP_NAME,
        AuthWorkerConfiguration.class, AuthConstants.AUTH_QUEUE);
  }
}
