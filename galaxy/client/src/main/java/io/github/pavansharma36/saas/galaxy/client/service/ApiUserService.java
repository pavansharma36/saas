package io.github.pavansharma36.saas.galaxy.client.service;

import io.github.pavansharma36.core.common.context.UserContext;
import io.github.pavansharma36.core.common.context.providers.UserContextProvider;
import io.github.pavansharma36.core.common.service.UserService;
import io.github.pavansharma36.saas.core.dto.common.UserDto;
import io.github.pavansharma36.saas.galaxy.api.UserApi;
import io.github.pavansharma36.saas.galaxy.client.GalaxyClientFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class ApiUserService implements UserService {

  private final UserApi userApi = GalaxyClientFactory.userApi();

  @Override
  public UserDto getUserById(String id) {
    return userApi.getUser(id).getData();
  }

  @PostConstruct
  public void postConstruct() {
    UserContextProvider.register(new UserContext(this));
  }
}
