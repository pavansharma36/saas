package io.github.pavansharma36.auth.api;

import io.github.pavansharma36.saas.auth.dto.authorization.UserAccessDto;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("self")
public interface SelfApi {

  @GetMapping("access")
  ResponseObject<UserAccessDto> access();

}
