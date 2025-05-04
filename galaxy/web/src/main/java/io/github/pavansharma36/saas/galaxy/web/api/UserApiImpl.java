package io.github.pavansharma36.saas.galaxy.web.api;

import io.github.pavansharma36.saas.core.dto.common.CreateUserDto;
import io.github.pavansharma36.saas.core.dto.common.UserDto;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import io.github.pavansharma36.saas.galaxy.api.UserApi;
import io.github.pavansharma36.saas.galaxy.common.service.GalaxyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiImpl implements UserApi {

  private final GalaxyUserService userService;

  @Override
  public ResponseObject<UserDto> createUser(@Valid CreateUserDto userDto) {
    return ResponseObject.response(userService.createUser(userDto));
  }

  @Override
  public ResponseObject<UserDto> getUser(String id) {
    return ResponseObject.response(userService.getUserById(id));
  }
}
