package io.github.pavansharma36.auth.api;

import io.github.pavansharma36.saas.auth.dto.UserAccountDto;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user_account")
public interface UserAccountApi {

  @PostMapping
  ResponseObject<UserAccountDto> createUserAccount(@RequestBody UserAccountDto userAccountDto);

}
