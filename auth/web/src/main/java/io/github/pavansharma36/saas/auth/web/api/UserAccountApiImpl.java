package io.github.pavansharma36.saas.auth.web.api;

import io.github.pavansharma36.auth.api.UserAccountApi;
import io.github.pavansharma36.saas.auth.common.service.AuthUserAccountService;
import io.github.pavansharma36.saas.auth.dto.UserAccountDetailsDto;
import io.github.pavansharma36.saas.core.dto.common.UserAccountDto;
import io.github.pavansharma36.saas.core.dto.response.ListResponseObject;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserAccountApiImpl implements UserAccountApi {

  private final AuthUserAccountService authUserAccountService;

  @Override
  public ListResponseObject<UserAccountDto> getUserAccounts(String username) {
    Optional<UserAccountDetailsDto> uad = authUserAccountService.userAccount(username);
    return ListResponseObject.success(
        uad.<java.util.List<UserAccountDto>>map(Collections::singletonList)
            .orElse(Collections.emptyList()));
  }

  @Override
  public ResponseObject<UserAccountDto> getUserAccount(String id) {
    return ResponseObject.response(authUserAccountService.getUserAccount(id));
  }

  @Override
  public ResponseObject<UserAccountDto> createUserAccount(@Valid UserAccountDto userAccountDto) {
    return ResponseObject.response(authUserAccountService.createUserAccount(userAccountDto));
  }

}
