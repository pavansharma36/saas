package io.github.pavansharma36.saas.core.server.security.context;

import io.github.pavansharma36.saas.core.server.security.jwt.JwtPayload;
import io.github.pavansharma36.saas.core.server.security.jwt.JwtService;
import io.github.pavansharma36.saas.utils.Constants;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Order(100)
@RequiredArgsConstructor
public class JwtSecurityContextProvider implements AppSecurityContextProvider {

  private static final JwtService jwtService = new JwtService();
  // private final UserDetailsService userDetailsService;

  public static void setJWTResponseHeader(HttpServletResponse response, UserDetails userDetails) {
    response.setHeader(Constants.AUTHORIZATION_HEADER,
        String.format("%s %s", Constants.AUTHORIZATION_TYPE_BEARER,
            jwtService.generate(userDetails)));
  }

  private void addNewAuthTokenIfRequired(HttpRequestResponseHolder holder,
                                         JwtPayload payload) {
    Date expiry = payload.getExpireAt();
    if (expiry.getTime() < (System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1))) {
      log.info("JWT is about to expire, generating new");

//      UserDetails user = userDetailsService.loadUserByUsername(payload.getUsername());
//      holder.getResponse().setHeader(Constants.AUTHORIZATION_HEADER,
//          String.format("%s %s", Constants.AUTHORIZATION_TYPE_BEARER, jwtService.generate(user)));
    }
  }

  @Override
  public Optional<Authentication> authentication(HttpRequestResponseHolder requestResponseHolder) {
    String auth = requestResponseHolder.getRequest().getHeader(Constants.AUTHORIZATION_HEADER);
    if (auth == null) {
      return Optional.empty();
    }

    String[] tokens = auth.split(" ");
    if (tokens.length != 2) {
      throw new ServerRuntimeException("Authorization must contain <TokenType/TokenValue>");
    }
    String jwt = null;
    if (tokens[0].equals(Constants.AUTHORIZATION_TYPE_BEARER)) {
      jwt = tokens[1];
    }
    JwtPayload payload = jwtService.parse(jwt);
    if (payload != null) {
      Authentication authentication = new UsernamePasswordAuthenticationToken(payload.getUsername(),
          null, payload.getRoles().stream()
          .map(r -> (GrantedAuthority) () -> r).collect(Collectors.toSet()));
      authentication.setAuthenticated(true);
      addNewAuthTokenIfRequired(requestResponseHolder, payload);
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
