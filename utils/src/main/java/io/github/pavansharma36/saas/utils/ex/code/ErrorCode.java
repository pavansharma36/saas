package io.github.pavansharma36.saas.utils.ex.code;

import java.util.Map;

public interface ErrorCode {

  String code();

  String title(Map<String, Object> params);

  String message(Map<String, Object> params);

}
