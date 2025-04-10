package io.github.pavansharma36.saas.utils.ex.code;

import java.util.Collections;
import java.util.Map;

public interface ErrorCode {

  String code();

  default String message() {
    return message(Collections.emptyMap());
  }

  String message(Map<String, Object> params);

}
