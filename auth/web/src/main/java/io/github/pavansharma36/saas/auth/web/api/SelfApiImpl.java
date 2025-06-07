package io.github.pavansharma36.saas.auth.web.api;

import io.github.pavansharma36.auth.api.SelfApi;
import io.github.pavansharma36.saas.auth.common.service.AuthSelfService;
import io.github.pavansharma36.saas.auth.dto.authorization.UserAccessDto;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SelfApiImpl implements SelfApi {

  private final AuthSelfService selfService;

  @Override
  public ResponseObject<UserAccessDto> access() {
    return ResponseObject.response(selfService.userAccess());
  }
}
