package io.github.pavansharma36.saas.auth.common.utils;

import org.springframework.security.core.GrantedAuthority;

public enum Role {
  TENANT_USER,
  TENANT_ADMIN,
  HOST_USER,
  HOST_ADMIN,
  ;

  public GrantedAuthority toGrantedAuthority() {
    return () -> "ROLE_" + name();
  }
}
