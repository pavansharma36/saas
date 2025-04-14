package io.github.pavansharma36.saas.core.client.feign;

import com.fasterxml.jackson.core.type.TypeReference;
import feign.FeignException;
import feign.Request;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import io.github.pavansharma36.saas.core.dto.ResponseObject;
import io.github.pavansharma36.saas.core.dto.ex.ResponseRuntimeException;
import io.github.pavansharma36.saas.utils.Constants;
import io.github.pavansharma36.saas.utils.collections.CollectionUtils;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

@Slf4j
public class Custom5xxErrorDecoder implements ErrorDecoder {

  private static final Set<Request.HttpMethod> NON_RETRYABLE_METHODS =
      Set.of(Request.HttpMethod.POST);
  private static final Set<Integer> CLIENT_ERROR_RETRY_STATUSES = Set.of(408, 425, 429);

  @Override
  public Exception decode(String methodKey, Response response) {
    FeignException exception = feign.FeignException.errorStatus(methodKey, response);
    log.error("Request failed : {}", exception.getMessage());
    int status = response.status();
    if (!NON_RETRYABLE_METHODS.contains(response.request().httpMethod()) &&
        (status >= 500 || CLIENT_ERROR_RETRY_STATUSES.contains(status))) {

      Long retryAfter = null;
      if (NumberUtils.isCreatable(
          CollectionUtils.nullSafe(response.headers().get(Constants.Header.RETRY_AFTER_HEADER))
              .stream().findAny()
              .orElse(null))) {
        retryAfter = NumberUtils.createLong(
            response.headers().get(Constants.Header.RETRY_AFTER_HEADER).stream().findAny()
                .orElse(null));
      }

      return new RetryableException(
          response.status(),
          exception.getMessage(),
          response.request().httpMethod(),
          exception,
          retryAfter,
          response.request());
    }

    if (isJsonResponse(response)) {
      String responseStr = exception.contentUTF8();
      if (StringUtils.isNotEmpty(responseStr)) {
        ResponseObject<Object> res = JsonUtils.fromJson(responseStr, new TypeReference<>() {
        });
        return new ResponseRuntimeException(exception.getMessage(), status, res);
      }
    } else {
      log.warn(
          "Request not retryable, throwing unknown error as content type is not application/json");
    }

    return exception;
  }

  private boolean isJsonResponse(Response response) {
    return response.headers().get(Constants.Header.CONTENT_TYPE).stream().findAny()
        .map(c -> c.startsWith("application/json")).orElse(false);
  }
}
