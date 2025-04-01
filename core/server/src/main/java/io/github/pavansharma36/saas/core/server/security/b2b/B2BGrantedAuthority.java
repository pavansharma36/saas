package io.github.pavansharma36.saas.core.server.security.b2b;

import org.springframework.security.core.GrantedAuthority;

public class B2BGrantedAuthority implements GrantedAuthority {

  public static final String ROLE_B2B = "ROLE_B2B";

  @Override
  public String getAuthority() {
    return ROLE_B2B;
  }
}
