package io.github.pavansharma36.saas.core.web.api;

import io.github.pavansharma36.saas.core.api.CoreApi;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import org.springframework.web.bind.annotation.RestController;

/**
 * core api which can be used in all web apps.
 */
@RestController
public class CoreApiImpl implements CoreApi {
  @Override
  public ResponseObject<String> ping() {
    return ResponseObject.response("PONG");
  }
}
