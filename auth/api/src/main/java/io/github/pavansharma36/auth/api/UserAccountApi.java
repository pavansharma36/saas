package io.github.pavansharma36.auth.api;

import io.github.pavansharma36.saas.core.dto.common.UserAccountDto;
import io.github.pavansharma36.saas.core.dto.response.ListResponseObject;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("user_account")
public interface UserAccountApi {

  @GetMapping
  ListResponseObject<UserAccountDto> getUserAccounts(
      @RequestParam(name = "username", required = false) String username);

  @GetMapping("{id}")
  ResponseObject<UserAccountDto> getUserAccount(@PathVariable("id") String id);

  @PostMapping
  ResponseObject<UserAccountDto> createUserAccount(@RequestBody UserAccountDto userAccountDto);

}
