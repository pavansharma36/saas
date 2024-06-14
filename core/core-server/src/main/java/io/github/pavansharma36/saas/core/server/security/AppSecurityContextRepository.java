package io.github.pavansharma36.saas.core.server.security;

import io.github.pavansharma36.saas.core.server.security.context.AppSecurityContextProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppSecurityContextRepository implements SecurityContextRepository {

  private final List<AppSecurityContextProvider> providers;

  @Override
  public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
    Set<GrantedAuthority> additionalAuthorities = new HashSet<>();
    for (AppSecurityContextProvider provider : providers) {
      additionalAuthorities.addAll(provider.additionalAuthorities(requestResponseHolder));
    }

    for (AppSecurityContextProvider provider : providers) {
      Optional<Authentication> authentication =
          provider.authentication(requestResponseHolder, additionalAuthorities);
      if (authentication.isPresent()) {
        return new SecurityContextImpl(authentication.get());
      }
    }
    return new SecurityContextImpl();
  }

  @Override
  public void saveContext(SecurityContext context, HttpServletRequest request,
                          HttpServletResponse response) {
    // nothing to do.
  }

  @Override
  public boolean containsContext(HttpServletRequest request) {
    for (AppSecurityContextProvider provider : providers) {
      if (provider.containsContext(request)) {
        return true;
      }
    }
    return false;
  }


}
