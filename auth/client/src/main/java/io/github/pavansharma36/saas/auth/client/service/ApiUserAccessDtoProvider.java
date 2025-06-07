package io.github.pavansharma36.saas.auth.client.service;

import io.github.pavansharma36.auth.api.SelfApi;
import io.github.pavansharma36.auth.api.authorization.UserAccessDtoProvider;
import io.github.pavansharma36.saas.auth.client.AuthClientFactory;
import io.github.pavansharma36.saas.auth.dto.authorization.UserAccessDto;
import org.springframework.stereotype.Component;

@Component
public class ApiUserAccessDtoProvider implements UserAccessDtoProvider {

  private final SelfApi selfApi = AuthClientFactory.selfApi();

  @Override
  public UserAccessDto access() {
    return selfApi.access().getData();
  }
}
