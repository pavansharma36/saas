package io.github.pavansharma36.saas.core.server.security;

import io.github.pavansharma36.saas.core.server.security.context.AppSecurityContextProvider;
import io.github.pavansharma36.saas.core.server.security.context.B2BSecurityContextProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppSecurityContextRepository implements SecurityContextRepository {

  private final List<AppSecurityContextProvider> providers;
  private final B2BSecurityContextProvider b2BSecurityContextProvider;

  public AppSecurityContextRepository(List<AppSecurityContextProvider> providers) {
    this.providers = providers;
    this.b2BSecurityContextProvider = new B2BSecurityContextProvider(providers);
  }

  @Override
  public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
    Optional<Authentication> auth = b2BSecurityContextProvider.authentication(requestResponseHolder);
    if (auth.isPresent()) {
      return new SecurityContextImpl(auth.get());
    }

    for (AppSecurityContextProvider provider : providers) {
      Optional<Authentication> authentication =
          provider.authentication(requestResponseHolder);
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
    if (b2BSecurityContextProvider.containsContext(request)) {
      return true;
    }
    for (AppSecurityContextProvider provider : providers) {
      if (provider.containsContext(request)) {
        return true;
      }
    }
    return false;
  }


}
