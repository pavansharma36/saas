package io.github.pavansharma36.saas.auth.web.security;

import io.github.pavansharma36.core.common.context.providers.RequestInfoContextProvider;
import io.github.pavansharma36.core.common.context.providers.UserContextProvider;
import io.github.pavansharma36.core.common.service.UserService;
import io.github.pavansharma36.saas.auth.common.dao.UserAccountDao;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.model.UserAccount;
import io.github.pavansharma36.saas.auth.common.utils.Role;
import io.github.pavansharma36.saas.core.dto.common.UserDto;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
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

      UserContextProvider.getInstance().set(userDto);
      RequestInfoContextProvider.getInstance().get().ifPresent(r -> {
        r.setUserId(userDto.getId());
        r.setTenantId(userDto.getTenantId());
      });

      return new User(ua.getUsername(), ua.getPassword(), userDto.isEnabled(),
          ua.isAccountNonExpired(),
          ua.getCredentialsExpireAt().getTime() > System.currentTimeMillis(),
          ua.isAccountNonLocked(),
          grantedAuthorityCollections(ua, userDto));
    }
    throw new UsernameNotFoundException("Username does not exists : " + username);
  }

  private List<GrantedAuthority> grantedAuthorityCollections(UserAccount userAccount,
                                                             UserDto userDto) {
    List<GrantedAuthority> a = new LinkedList<>();
    if (userDto.getTenantId() == null) {
      a.add(Role.HOST_USER.toGrantedAuthority());
      if (userAccount.isAdmin()) {
        a.add(Role.HOST_ADMIN.toGrantedAuthority());
      }
    } else {
      a.add(Role.TENANT_USER.toGrantedAuthority());
      if (userAccount.isAdmin()) {
        a.add(Role.TENANT_ADMIN.toGrantedAuthority());
      }
    }
    return a;
  }

}
