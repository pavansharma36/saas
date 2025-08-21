package io.github.pavansharma36.saas.core.common.service;

import io.github.pavansharma36.saas.core.dto.common.UserDto;
import org.springframework.cache.annotation.Cacheable;

public interface UserService {

  String CACHE_NAME = "user";

  @Cacheable(cacheNames = CACHE_NAME, key = "T(io.github.pavansharma36.core.common.utils.CoreUtils).getUserIdOrThrow()")
  UserDto getCurrentUser();

  @Cacheable(cacheNames = CACHE_NAME, key = "#id")
  UserDto getUserById(String id);

}
