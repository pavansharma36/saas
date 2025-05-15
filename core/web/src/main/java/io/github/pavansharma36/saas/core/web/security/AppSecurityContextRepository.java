package io.github.pavansharma36.saas.core.web.security;

import io.github.pavansharma36.core.common.service.TenantService;
import io.github.pavansharma36.core.common.service.UserService;
import io.github.pavansharma36.saas.core.web.security.context.AppSecurityContextProvider;
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

  public AppSecurityContextRepository(
      TenantService tenantService,
      UserService userService,
      List<AppSecurityContextProvider> providers) {
    this.providers = providers;
  }

  @Override
  public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
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
    for (AppSecurityContextProvider provider : providers) {
      if (provider.containsContext(request)) {
        return true;
      }
    }
    return false;
  }


}
