package io.github.pavansharma36.saas.auth.client.service;

import io.github.pavansharma36.auth.api.UserAccountApi;
import io.github.pavansharma36.saas.core.common.service.UserAccountService;
import io.github.pavansharma36.saas.auth.client.AuthClientFactory;
import io.github.pavansharma36.saas.core.dto.common.UserAccountDto;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ApiUserAccountService implements UserAccountService<UserAccountDto> {

  private final UserAccountApi userAccountApi = AuthClientFactory.userAccountApi();

  @Override
  public UserAccountDto getUserAccount(String id) {
    return userAccountApi.getUserAccount(id).getData();
  }

  @Override
  public Optional<UserAccountDto> userAccount(String username) {
    return userAccountApi.getUserAccounts(username).getData().stream().findAny();
  }
}
