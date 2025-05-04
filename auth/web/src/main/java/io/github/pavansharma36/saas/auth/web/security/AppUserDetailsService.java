package io.github.pavansharma36.saas.auth.web.security;

import io.github.pavansharma36.core.common.service.BaseService;
import io.github.pavansharma36.saas.auth.common.dao.UserAccountDao;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.model.UserAccount;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class AppUserDetailsService extends BaseService implements UserDetailsService {

  private final UserAccountDao userAccountDao;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    logger.info("Looking into mongo for username : {}", username);
    Optional<UserAccount> oua = userAccountDao.userAccount(username);
    if (oua.isPresent()) {
      UserAccount ua = oua.get();

      return new User(ua.getUsername(), ua.getPassword(), ua.isEnabled(),
          ua.isAccountNonExpired(), ua.isCredentialsNonExpired(), ua.isAccountNonLocked(),
          Collections.emptyList());
    }
    throw new UsernameNotFoundException("Username does not exists : " + username);
  }

}
