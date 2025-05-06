package io.github.pavansharma36.saas.auth.web.security;

import io.github.pavansharma36.core.common.service.UserService;
import io.github.pavansharma36.saas.auth.common.dao.UserAccountDao;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.model.UserAccount;
import io.github.pavansharma36.saas.core.dto.common.UserDto;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

  private final UserAccountDao userAccountDao;
  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    log.info("Looking into postgres for username : {}", username);
    Optional<UserAccount> oua = userAccountDao.userAccount(username);
    if (oua.isPresent()) {
      UserAccount ua = oua.get();
      UserDto userDto = userService.getUserById(ua.getId());

      return new User(ua.getUsername(), ua.getPassword(), userDto.isEnabled(),
          ua.isAccountNonExpired(), ua.isCredentialsNonExpired(), ua.isAccountNonLocked(),
          Collections.emptyList());
    }
    throw new UsernameNotFoundException("Username does not exists : " + username);
  }

}
