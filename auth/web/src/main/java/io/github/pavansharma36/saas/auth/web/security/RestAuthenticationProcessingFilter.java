package io.github.pavansharma36.saas.auth.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pavansharma36.saas.auth.dto.LoginDto;
import io.github.pavansharma36.saas.auth.web.dao.UserSessionDao;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class RestAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

  private final ObjectMapper mapper = JsonUtils.mapper();

  public RestAuthenticationProcessingFilter(AuthenticationManager authenticationManager,
                                            UserSessionDao userSessionDao,
                                            RememberMeServices rememberMeServices) {
    super(new AntPathRequestMatcher("/api/login", "POST"));
    setAuthenticationManager(authenticationManager);
    setAuthenticationSuccessHandler(
        new LogInSuccessHandler(userSessionDao, rememberMeServices));
    setAuthenticationFailureHandler(new LogInFailureHandler());
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response)
      throws AuthenticationException, IOException {
    LoginDto dto = mapper.readValue(request.getInputStream(), LoginDto.class);
    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword(),
            Collections.emptyList());
    return getAuthenticationManager().authenticate(token);
  }
}
