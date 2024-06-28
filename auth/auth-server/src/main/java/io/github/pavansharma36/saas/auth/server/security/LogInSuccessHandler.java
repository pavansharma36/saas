package io.github.pavansharma36.saas.auth.server.security;

import io.github.pavansharma36.saas.auth.server.dao.UserSessionDao;
import io.github.pavansharma36.saas.core.dto.ResponseObject;
import io.github.pavansharma36.saas.core.server.security.jwt.JwtService;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;

public class LogInSuccessHandler implements AuthenticationSuccessHandler {

  private final UserSessionDao userSessionDaoService;
  private final RememberMeServices rememberMeServices;
  private final JwtService jwtService = new JwtService();

  public LogInSuccessHandler(final UserSessionDao userSessionDaoService,
                             final RememberMeServices rememberMeServices) {
    this.userSessionDaoService = userSessionDaoService;
    this.rememberMeServices = rememberMeServices;
  }

  @Override
  public void onAuthenticationSuccess(final HttpServletRequest request,
                                      final HttpServletResponse response,
                                      final Authentication authentication)
      throws IOException, ServletException {
    final UserDetails account = (UserDetails) authentication.getPrincipal();
//		final UserSession userSession = new UserSession();
//		userSession.setIpAddress(request.getRemoteAddr());
//		userSession.setSessionId(request.getSession().getId());
//		userSession.setUserAccountId(account.getId());
//		userSessionDaoService.save(userSession);

    response.setHeader("auth", jwtService.generate(account));
    response.setContentType("application/json");
    rememberMeServices.loginSuccess(request, response, authentication);
    response.getWriter().write(JsonUtils.mapper().writeValueAsString(ResponseObject.success(true)));
  }

}
