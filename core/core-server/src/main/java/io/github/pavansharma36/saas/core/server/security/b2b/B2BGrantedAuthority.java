package io.github.pavansharma36.saas.core.server.security.b2b;

import org.springframework.security.core.GrantedAuthority;

public class B2BGrantedAuthority implements GrantedAuthority {
  @Override
  public String getAuthority() {
    return "B2B_USER";
  }
}
