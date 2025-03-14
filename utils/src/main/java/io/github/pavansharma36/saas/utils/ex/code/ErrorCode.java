package io.github.pavansharma36.saas.utils.ex.code;

import java.util.Map;

public interface ErrorCode {

  String code();

  String uiMessage(Map<String, Object> params);

}
