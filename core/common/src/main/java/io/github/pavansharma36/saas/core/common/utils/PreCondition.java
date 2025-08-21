package io.github.pavansharma36.saas.core.common.utils;

import io.github.pavansharma36.saas.core.common.validation.ErrorCodeException;
import io.github.pavansharma36.saas.utils.ex.code.ErrorCode;
import java.util.Collections;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class PreCondition {

  public static void assertCondition(boolean val, ErrorCode errorCode) {
    assertCondition(val, errorCode, 500);
  }

  public static void assertCondition(boolean val, ErrorCode errorCode, int status) {
    assertCondition(val, errorCode, Collections.emptyMap(), status);
  }

  public static void assertCondition(boolean val, ErrorCode errorCode, Map<String, Object> params,
                                     int status) {
    if (!val) {
      throw new ErrorCodeException(errorCode, params, status);
    }
  }

  public static void assertNotNull(Object obj, ErrorCode errorCode) {
    PreCondition.assertNotNull(obj, errorCode, 500);
  }

  public static void assertNotNull(Object obj, ErrorCode errorCode, int status) {
    PreCondition.assertNotNull(obj, errorCode, Collections.emptyMap(), status);
  }

  public static void assertNotNull(Object obj, ErrorCode errorCode, Map<String, Object> params,
                                   int status) {
    assertCondition(obj != null, errorCode, params, status);
  }

  public static void assertNull(Object obj, ErrorCode errorCode) {
    PreCondition.assertNull(obj, errorCode, 500);
  }

  public static void assertNull(Object obj, ErrorCode errorCode, int status) {
    PreCondition.assertNull(obj, errorCode, Collections.emptyMap(), status);
  }

  public static void assertNull(Object obj, ErrorCode errorCode, Map<String, Object> params,
                                int status) {
    assertCondition(obj == null, errorCode, params, status);
  }


}
