package io.github.pavansharma36.saas.core.common.mutex.dao;

import io.github.pavansharma36.saas.utils.ex.AppRuntimeException;

public class DuplicateModelException extends AppRuntimeException {

  public DuplicateModelException(String message, Throwable cause) {
    super(message, cause);
  }

}
