package io.github.pavansharma36.saas.core.server.security.b2b;

import java.util.Collections;
import java.util.stream.Stream;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;

public class B2BAuthentication extends AbstractAuthenticationToken {

  private final Authentication delegation;

  public B2BAuthentication() {
    super(Collections.singleton(new B2BGrantedAuthority()));
    delegation = null;
    setAuthenticated(true);
  }

  public B2BAuthentication(Authentication delegation) {
    super(Stream.concat(delegation.getAuthorities().stream(), Stream.of(new B2BGrantedAuthority())).toList());
    this.delegation = delegation;
    setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    if (delegation != null) {
      return delegation.getPrincipal();
    }
    return (AuthenticatedPrincipal) () -> "System";
  }
}
