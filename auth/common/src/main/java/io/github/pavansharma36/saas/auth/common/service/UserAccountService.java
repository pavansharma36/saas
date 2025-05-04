package io.github.pavansharma36.saas.auth.common.service;

import io.github.pavansharma36.core.common.validation.ValidationException;
import io.github.pavansharma36.saas.auth.common.dao.UserAccountDao;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.model.UserAccount;
import io.github.pavansharma36.saas.auth.common.utils.AuthErrorCode;
import io.github.pavansharma36.saas.auth.dto.UserAccountDto;
import io.github.pavansharma36.saas.utils.Utils;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAccountService {

  private final PasswordEncoder passwordEncoder;
  private final UserAccountDao userAccountDao;

  public UserAccountDto createUserAccount(UserAccountDto userAccountDto) {
    if (userAccountDao.userAccount(userAccountDto.getUsername()).isPresent()) {
      throw new ValidationException(UserAccountDto.FIELD_USERNAME,
          AuthErrorCode.USERNAME_ALREADY_EXISTS,
          Collections.singletonMap(UserAccountDto.FIELD_USERNAME, userAccountDto.getUsername()));
    }
    UserAccount userAccount = new UserAccount();
    userAccount.setUsername(userAccountDto.getUsername());
    userAccount.setUserInfoId(userAccountDto.getUserInfoId());
    userAccount.setEnabled(true);
    userAccount.setAccountNonExpired(true);
    userAccount.setAccountNonLocked(true);

    userAccount.setPassword(passwordEncoder.encode(Utils.randomRequestId()));
    userAccount.setCredentialsNonExpired(false);
    UserAccount account = userAccountDao.insert(userAccount);

    userAccountDto.setId(account.getId());
    return userAccountDto;
  }

}
