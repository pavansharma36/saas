package io.github.pavansharma36.saas.galaxy.api;

import io.github.pavansharma36.saas.core.dto.common.CreateUserDto;
import io.github.pavansharma36.saas.core.dto.common.UserDto;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user")
public interface UserApi {

  @PostMapping
  ResponseObject<UserDto> createUser(@RequestBody CreateUserDto userDto);

  @GetMapping("me")
  ResponseObject<UserDto> getCurrentUser();

  @GetMapping("{id}")
  ResponseObject<UserDto> getUserById(@PathVariable String id);

}
