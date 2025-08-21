package io.github.pavansharma36.saas.core.broker.consumer.processor;

import io.github.pavansharma36.saas.core.common.validation.ServerRuntimeException;

public class ProcessorNotFoundException extends ServerRuntimeException {
  public ProcessorNotFoundException(String message) {
    super(message);
  }
}
