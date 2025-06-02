package io.github.pavansharma36.saas.auth.web.security;

import io.github.pavansharma36.saas.auth.web.dao.UserSessionDao;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import io.github.pavansharma36.saas.core.web.security.context.JwtSecurityContextProvider;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LogInSuccessHandler implements AuthenticationSuccessHandler {

  private final UserSessionDao userSessionDaoService;

  public LogInSuccessHandler(
      final UserSessionDao userSessionDaoService) {
    this.userSessionDaoService = userSessionDaoService;
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

    JwtSecurityContextProvider.addNewAuthToken(response, account);
    response.setContentType("application/json");
    response.getWriter().write(JsonUtils.mapper().writeValueAsString(ResponseObject.empty()));
  }

}
