package io.github.pavansharma36.saas.core.web.security.b2b;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.GrantedAuthority;

public class UserIdAuthentication extends AbstractAuthenticationToken {

  private final String userId;

  public UserIdAuthentication(String userId, Collection<GrantedAuthority> grantedAuthorities) {
    super(grantedAuthorities);
    this.userId = userId;
    setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return (AuthenticatedPrincipal) () -> userId;
  }
}
