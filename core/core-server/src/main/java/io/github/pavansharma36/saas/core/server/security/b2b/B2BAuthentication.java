package io.github.pavansharma36.saas.core.server.security.b2b;

import java.util.Collections;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.AuthenticatedPrincipal;

public class B2BAuthentication extends AbstractAuthenticationToken {

  public B2BAuthentication() {
    super(Collections.singleton(new B2BGrantedAuthority()));
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return (AuthenticatedPrincipal) () -> "System";
  }
}
