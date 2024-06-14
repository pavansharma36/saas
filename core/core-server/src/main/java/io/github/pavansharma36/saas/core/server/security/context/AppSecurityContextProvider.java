package io.github.pavansharma36.saas.core.server.security.context;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.context.HttpRequestResponseHolder;

public interface AppSecurityContextProvider {

  // first we query additional authorities as internal calls will be made with jwt and app to app token.
  // such request will have user authorities aswell as app to app authorities.
  default Collection<GrantedAuthority> additionalAuthorities(
      HttpRequestResponseHolder requestResponseHolder) {
    return Collections.emptyList();
  }

  Optional<Authentication> authentication(HttpRequestResponseHolder requestResponseHolder,
                                          Set<GrantedAuthority> additionalAuthorities);

  boolean containsContext(HttpServletRequest request);

}
