package io.github.pavansharma36.saas.core.server.api;

import io.github.pavansharma36.saas.core.api.CoreApi;
import io.github.pavansharma36.saas.core.dto.ResponseObject;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoreApiImpl implements CoreApi {
  @Override
  public ResponseObject<String> ping() {
    return ResponseObject.success("PONG");
  }
}
