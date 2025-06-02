package io.github.pavansharma36.saas.auth.common.service;

import io.github.pavansharma36.core.common.service.UserAccountService;
import io.github.pavansharma36.core.common.validation.ValidationException;
import io.github.pavansharma36.saas.auth.common.dao.UserAccountDao;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.model.UserAccount;
import io.github.pavansharma36.saas.auth.common.utils.AuthErrorCode;
import io.github.pavansharma36.saas.auth.dto.UserAccountDetailsDto;
import io.github.pavansharma36.saas.core.dto.common.UserAccountDto;
import io.github.pavansharma36.saas.utils.Utils;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthUserAccountService implements UserAccountService<UserAccountDetailsDto> {

  private final PasswordEncoder passwordEncoder;
  private final UserAccountDao userAccountDao;

  public UserAccountDto createUserAccount(UserAccountDto userAccountDto) {
    if (userAccountDao.userAccount(userAccountDto.getUsername()).isPresent()) {
      Map<String, Object> params =
          Map.of(UserAccountDto.FIELD_USERNAME, userAccountDto.getUsername(),
              ValidationException.ErrorCodeDetails.FIELD, UserAccountDto.FIELD_USERNAME);
      throw new ValidationException(UserAccountDto.FIELD_USERNAME,
          AuthErrorCode.USERNAME_ALREADY_EXISTS, params);
    }
    UserAccount userAccount = new UserAccount();
    userAccount.setId(userAccountDto.getId());
    userAccount.setUsername(userAccountDto.getUsername());
    userAccount.setAccountNonExpired(true);
    userAccount.setAccountNonLocked(true);

    userAccount.setPassword(passwordEncoder.encode(Utils.randomRequestId()));
    userAccount.setCredentialsExpireAt(
        new Date(System.currentTimeMillis() + Duration.ofDays(365).toMillis()));
    UserAccount account = userAccountDao.insert(userAccount);

    userAccountDto.setId(account.getId());
    return userAccountDto;
  }

  @Override
  public UserAccountDto getUserAccount(String id) {
    UserAccount userAccount = userAccountDao.findByIdOrThrow(id);

    UserAccountDto dto = new UserAccountDto();
    dto.setId(userAccount.getId());
    dto.setUsername(userAccount.getUsername());
    dto.setAuthorities(Collections.emptyList());
    return dto;
  }

  @Override
  public Optional<UserAccountDetailsDto> userAccount(String username) {
    return Optional.empty();
  }


}
