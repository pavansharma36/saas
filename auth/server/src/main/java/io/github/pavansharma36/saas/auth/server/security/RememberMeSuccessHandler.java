package io.github.pavansharma36.saas.auth.server.security;

import io.github.pavansharma36.saas.core.server.security.context.JwtSecurityContextProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class RememberMeSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication)
      throws IOException, ServletException {
    JwtSecurityContextProvider.setJWTResponseHeader(response,
        (UserDetails) authentication.getPrincipal());
    // Navigate to original request after adding auth token.
    request.getRequestDispatcher(request.getRequestURI()).forward(request, response);
  }
}
