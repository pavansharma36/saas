package io.github.pavansharma36.core.common.service;

import io.github.pavansharma36.saas.core.dto.common.UserAccountDto;
import java.util.Optional;

public interface UserAccountService<T extends UserAccountDto> {
  UserAccountDto getUserAccount(String id);

  Optional<T> userAccount(String username);
}
