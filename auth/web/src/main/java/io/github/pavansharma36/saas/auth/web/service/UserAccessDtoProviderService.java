package io.github.pavansharma36.saas.auth.web.service;

import io.github.pavansharma36.auth.api.authorization.UserAccessDtoProvider;
import io.github.pavansharma36.saas.auth.common.service.AuthSelfService;
import io.github.pavansharma36.saas.auth.dto.authorization.UserAccessDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccessDtoProviderService implements UserAccessDtoProvider {

  private final AuthSelfService service;

  @Override
  public UserAccessDto access() {
    return service.userAccess();
  }
}
