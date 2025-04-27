package io.github.pavansharma36.core.common.mutex.dao;

import io.github.pavansharma36.saas.utils.ex.AppException;

public class DuplicateModelException extends AppException {

  protected DuplicateModelException(String message, Throwable cause) {
    super(message, cause);
  }
  
}
