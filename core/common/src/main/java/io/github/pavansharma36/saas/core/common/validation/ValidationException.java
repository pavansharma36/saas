package io.github.pavansharma36.saas.core.common.validation;

import io.github.pavansharma36.saas.utils.ex.AppRuntimeException;
import io.github.pavansharma36.saas.utils.ex.code.ErrorCode;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

public class ValidationException extends AppRuntimeException {

  @Getter
  private final List<ErrorCodeDetails> errorCodes;

  public ValidationException(String field,
                             ErrorCode errorCode,
                             Map<String, Object> codeParam) {
    super(CoreErrorCode.VALIDATION_ERROR.message());
    this.errorCodes = Collections.singletonList(ErrorCodeDetails.builder()
        .errorCode(errorCode).params(codeParam).field(field)
        .build());
  }

  public ValidationException(List<ErrorCodeDetails> errorCodes) {
    super(CoreErrorCode.VALIDATION_ERROR.message());
    this.errorCodes = errorCodes;
  }


  @Override
  public int statusCode() {
    return 400;
  }

  @Getter
  @Builder
  public static class ErrorCodeDetails {

    public static final String FIELD = "field";

    private final ErrorCode errorCode;
    private final Map<String, Object> params;
    private final String field;
  }
}
