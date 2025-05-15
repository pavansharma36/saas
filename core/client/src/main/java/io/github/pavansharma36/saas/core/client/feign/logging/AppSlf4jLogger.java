package io.github.pavansharma36.saas.core.client.feign.logging;

import feign.slf4j.Slf4jLogger;
import io.github.pavansharma36.saas.utils.Constants;
import java.util.Set;

public class AppSlf4jLogger extends Slf4jLogger {

  private static final Set<String> SENSITIVE_HEADERS = Constants.Header.SENSITIVE_HEADERS;

  public AppSlf4jLogger() {
    super("FeignClient");
  }

  @Override
  protected boolean shouldLogRequestHeader(String header) {
    return !SENSITIVE_HEADERS.contains(header);
  }

  @Override
  protected boolean shouldLogResponseHeader(String header) {
    return !SENSITIVE_HEADERS.contains(header);
  }
}
