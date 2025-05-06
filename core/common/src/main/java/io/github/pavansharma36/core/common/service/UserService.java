package io.github.pavansharma36.core.common.service;

import io.github.pavansharma36.saas.core.dto.common.UserDto;

public interface UserService {

  UserDto getUserById(String id);

  default UserDto createSystemUser() {
    UserDto dto = new UserDto();
    dto.setId("system");
    dto.setFirstName("system");
    dto.setLastName("system");
    dto.setEnabled(true);
    return dto;
  }

}
