package io.github.pavansharma36.saas.auth.server.security;

import io.github.pavansharma36.core.common.service.BaseService;
import io.github.pavansharma36.saas.auth.common.dao.RoleDao;
import io.github.pavansharma36.saas.auth.common.dao.UserAccountDao;
import io.github.pavansharma36.saas.auth.common.dao.UserAccountRoleDao;
import io.github.pavansharma36.saas.auth.common.model.Role;
import io.github.pavansharma36.saas.auth.common.model.UserAccount;
import io.github.pavansharma36.saas.auth.common.model.UserAccountRole;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class AppUserDetailsService extends BaseService implements UserDetailsService {

  private final UserAccountDao userAccountDao;
  private final UserAccountRoleDao userAccountRoleDao;
  private final RoleDao roleDao;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    logger.info("Looking into mongo for username : {}", username);
    Optional<UserAccount> oua = userAccountDao.userAccount(username);
    if (oua.isPresent()) {
      UserAccount ua = oua.get();
      List<UserAccountRole> urs = userAccountRoleDao.findByUserAccountId(ua.getId());
      List<Role> roles;
      if (!urs.isEmpty()) {
        roles = roleDao.findByIds(urs.stream().map(UserAccountRole::getRoleId).toList());
      } else {
        roles = Collections.emptyList();
      }

      return new User(ua.getUsername(), ua.getPassword(), ua.isEnabled(),
          ua.isAccountNonExpired(), ua.isCredentialsNonExpired(), ua.isAccountNonLocked(),
          roles.stream().map(r -> (GrantedAuthority) r::getRole).collect(Collectors.toList()));
    }
    throw new UsernameNotFoundException("Username does not exists : " + username);
  }

}
