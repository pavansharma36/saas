package io.github.pavansharma36.saas.auth.web.api;

import io.github.pavansharma36.auth.api.UserAccountApi;
import io.github.pavansharma36.saas.auth.common.service.UserAccountService;
import io.github.pavansharma36.saas.auth.dto.UserAccountDto;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserAccountApiImpl implements UserAccountApi {

  private final UserAccountService userAccountService;

  @Override
  public ResponseObject<UserAccountDto> createUserAccount(@Valid UserAccountDto userAccountDto) {
    return ResponseObject.response(userAccountService.createUserAccount(userAccountDto));
  }
  
}
