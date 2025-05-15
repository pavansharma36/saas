package io.github.pavansharma36.saas.core.client.feign.retry;

import com.fasterxml.jackson.core.type.TypeReference;
import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import io.github.pavansharma36.saas.core.client.AbstractBaseRetry;
import io.github.pavansharma36.saas.core.dto.ex.ResponseRuntimeException;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import io.github.pavansharma36.saas.utils.Constants;
import io.github.pavansharma36.saas.utils.collections.CollectionUtils;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.hc.client5.http.utils.DateUtils;

@Slf4j
public class Custom5xxErrorDecoder extends AbstractBaseRetry implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    FeignException exception = feign.FeignException.errorStatus(methodKey, response);
    log.error("Request failed : {}", exception.getMessage());
    int status = response.status();
    if (!NON_RETRYABLE_METHODS.contains(response.request().httpMethod()) &&
        (status >= 500 || CLIENT_ERROR_RETRY_STATUSES.contains(status))) {

      Long retryAfter = null;
      String retryAfterHeader =
          CollectionUtils.nullSafe(response.headers().get(Constants.Header.RETRY_AFTER_HEADER))
              .stream().findAny()
              .orElse(null);
      if (retryAfterHeader != null) {
        if (NumberUtils.isCreatable(retryAfterHeader)) {
          retryAfter = NumberUtils.createLong(
              response.headers().get(Constants.Header.RETRY_AFTER_HEADER).stream().findAny()
                  .orElse(null));
        } else {
          Instant retryAfterDate = DateUtils.parseStandardDate(retryAfterHeader);
          if (retryAfterDate != null) {
            retryAfter =
                TimeUnit.MILLISECONDS.toSeconds(
                    retryAfterDate.toEpochMilli() - System.currentTimeMillis());
          }
        }
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
