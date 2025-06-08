package io.github.pavansharma36.saas.core.web.security.context;

import io.github.pavansharma36.core.common.context.providers.RequestInfoContextProvider;
import io.github.pavansharma36.core.common.utils.CoreUtils;
import io.github.pavansharma36.saas.core.web.security.jwt.JwtDetails;
import io.github.pavansharma36.saas.core.web.security.jwt.JwtService;
import io.github.pavansharma36.saas.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Order(100)
@RequiredArgsConstructor
public class JwtSecurityContextProvider implements AppSecurityContextProvider {

  private static final JwtService jwtService = new JwtService();
  private final UserDetailsService userDetailsService;

  public static void addNewAuthToken(HttpServletResponse response, UserDetails userDetails) {
    response.setHeader(Constants.Header.AUTHORIZATION_HEADER,
        CoreUtils.authorizationHeader(Constants.AUTHORIZATION_TYPE_BEARER,
            jwtService.generate(RequestInfoContextProvider.getInstance().getOrThrow().getUserId(),
                RequestInfoContextProvider.getInstance().getOrThrow().getTenantId(),
                userDetails)));
  }

  private void addNewAuthTokenIfRequired(HttpRequestResponseHolder holder,
                                         JwtDetails payload) {
    Date expiry = payload.getExpireAt();
    if (expiry.getTime() < (System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1))) {
      log.info("JWT is about to expire, generating new");

      RequestInfoContextProvider.getInstance().getOrThrow()
          .setResponseJwt(jwtService.generate(payload));
    }
  }

  @Override
  public Optional<Authentication> authentication(HttpRequestResponseHolder requestResponseHolder) {
    String auth =
        requestResponseHolder.getRequest().getHeader(Constants.Header.AUTHORIZATION_HEADER);
    if (auth == null) {
      return Optional.empty();
    }

    Pair<String, String> authorization = CoreUtils.parseAuthorizationHeader(auth);
    JwtDetails jwtDetails = jwtService.parse(authorization.getRight());
    if (jwtDetails != null) {
      Authentication authentication = new UsernamePasswordAuthenticationToken(
          (AuthenticatedPrincipal) jwtDetails::getUsername,
          null, jwtDetails.getPayload().getAuthorities().stream()
          .map(r -> (GrantedAuthority) () -> r).collect(Collectors.toSet()));

      addNewAuthTokenIfRequired(requestResponseHolder, jwtDetails);

      RequestInfoContextProvider.getInstance().get().ifPresent(r -> {
        r.setUserId(jwtDetails.getPayload().getUserId());
        r.setRoles(jwtDetails.getPayload().getAuthorities());
      });

      return Optional.of(authentication);
    }
    return Optional.empty();
  }

  @Override
  public boolean containsContext(HttpServletRequest request) {
    String header = request.getHeader(Constants.Header.AUTHORIZATION_HEADER);
    return header != null && header.startsWith(Constants.AUTHORIZATION_TYPE_BEARER);
  }
}
