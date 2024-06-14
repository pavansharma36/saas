package io.github.pavansharma36.saas.core.server.security.context;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.core.server.security.b2b.B2BAuthentication;
import io.github.pavansharma36.saas.core.server.security.b2b.B2BGrantedAuthority;
import io.github.pavansharma36.saas.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.stereotype.Service;

@Service
public class B2BSecurityContextProvider implements AppSecurityContextProvider {

  private static final String B2B_HEADER_VALUE = Config.get(Constants.B2B_SECRET_CONF);

  @Override
  public Collection<GrantedAuthority> additionalAuthorities(
      HttpRequestResponseHolder requestResponseHolder) {
    String header = requestResponseHolder.getRequest().getHeader(Constants.B2B_SECRET_HEADER);
    if (header != null && header.equals(B2B_HEADER_VALUE)) {
      return Collections.singleton(new B2BGrantedAuthority());
    }
    return Collections.emptyList();
  }

  @Override
  public Optional<Authentication> authentication(HttpRequestResponseHolder requestResponseHolder,
                                                 Set<GrantedAuthority> additionalAuthorities) {
    String header = requestResponseHolder.getRequest().getHeader(Constants.B2B_SECRET_HEADER);
    if (header != null && header.equals(B2B_HEADER_VALUE)) {
      return Optional.of(new B2BAuthentication());
    }

    return Optional.empty();
  }

  @Override
  public boolean containsContext(HttpServletRequest request) {
    return request.getHeader(Constants.B2B_SECRET_HEADER) != null;
  }
}
