package io.github.pavansharma36.saas.galaxy.common.service;

import io.github.pavansharma36.core.common.context.UserContext;
import io.github.pavansharma36.core.common.context.providers.UserContextProvider;
import io.github.pavansharma36.core.common.service.UserService;
import io.github.pavansharma36.saas.core.dto.common.UserDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class GalaxyUserService implements UserService {
  @Override
  public UserDto getUserById(String id) {
    UserDto dto = new UserDto();
    dto.setId(id);
    return dto;
  }


  @PostConstruct
  public void postConstruct() {
    UserContextProvider.register(new UserContext(this));
  }
}
