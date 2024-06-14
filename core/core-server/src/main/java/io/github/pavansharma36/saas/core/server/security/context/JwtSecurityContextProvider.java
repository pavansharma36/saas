package io.github.pavansharma36.saas.core.server.security.context;

import io.github.pavansharma36.saas.core.server.security.jwt.JwtPayload;
import io.github.pavansharma36.saas.core.server.security.jwt.JwtService;
import io.github.pavansharma36.saas.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Order(100)
public class JwtSecurityContextProvider implements AppSecurityContextProvider {

  private final JwtService jwtService = new JwtService();

  private void addNewAuthTokenIfRequired(HttpRequestResponseHolder holder,
                                         Authentication authentication, Date expiry) {
    if (expiry.getTime() < (System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1))) {
      log.info("JWT is about to expire, generating new");
      holder.getResponse().setHeader("auth", jwtService.generate(new User(authentication.getName(),
          "", authentication.getAuthorities())));
    }
  }

  @Override
  public Optional<Authentication> authentication(HttpRequestResponseHolder requestResponseHolder,
                                                 Set<GrantedAuthority> additionalAuthorities) {
    Pair<JwtPayload, Date> response =
        jwtService.parse(
            requestResponseHolder.getRequest().getHeader(Constants.AUTHORIZATION_HEADER));
    if (response != null) {
      JwtPayload payload = response.getLeft();
      Collection<GrantedAuthority> authorities = new HashSet<>(additionalAuthorities);
      authorities.addAll(payload.getRoles().stream().map(r -> (GrantedAuthority) () -> r)
          .toList());
      Authentication authentication = new UsernamePasswordAuthenticationToken(payload.getUsername(),
          null, authorities);
      addNewAuthTokenIfRequired(requestResponseHolder, authentication, response.getRight());
      return Optional.of(authentication);
    }
    return Optional.empty();
  }

  @Override
  public boolean containsContext(HttpServletRequest request) {
    String header = request.getHeader(Constants.AUTHORIZATION_HEADER);
    return header != null && header.startsWith(Constants.AUTHORIZATION_TYPE_BEARER);
  }
}
