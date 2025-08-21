package io.github.pavansharma36.saas.core.web.security.context;

import io.github.pavansharma36.saas.core.common.config.Config;
import io.github.pavansharma36.saas.core.common.context.providers.RequestInfoContextProvider;
import io.github.pavansharma36.saas.core.common.crypto.CryptUtil;
import io.github.pavansharma36.saas.core.common.crypto.KeyType;
import io.github.pavansharma36.saas.core.common.utils.CoreConstants;
import io.github.pavansharma36.saas.core.web.security.b2b.B2BAuthentication;
import io.github.pavansharma36.saas.core.web.security.b2b.UserIdAuthentication;
import io.github.pavansharma36.saas.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.stereotype.Service;

@Service
@Order(200)
public class B2BSecurityContextProvider
    implements AppSecurityContextProvider {

  private static final String B2B_HEADER_VALUE = Config.get(Constants.B2B_SECRET_CONF);

  @Override
  public Optional<Authentication> authentication(HttpRequestResponseHolder requestResponseHolder) {
    String header =
        requestResponseHolder.getRequest().getHeader(Constants.Header.B2B_SECRET_HEADER);
    if (header != null &&
        B2B_HEADER_VALUE.equals(CryptUtil.decryptQuietly(KeyType.DEFAULT, header).orElse(null))) {
      String tenantId =
          requestResponseHolder.getRequest().getHeader(Constants.Header.TENANT_ID_HEADER);
      if (tenantId != null) {
        RequestInfoContextProvider.getInstance().getOrThrow().setTenantId(tenantId);
      }

      String userId = requestResponseHolder.getRequest().getHeader(Constants.Header.USER_ID_HEADER);
      if (userId != null) {
        RequestInfoContextProvider.getInstance().getOrThrow().setUserId(userId);

        String rolesHeader =
            requestResponseHolder.getRequest().getHeader(Constants.Header.USER_ROLES_HEADER);
        List<GrantedAuthority> grantedAuthorities;
        if (rolesHeader != null) {
          grantedAuthorities = Arrays.stream(rolesHeader.split(","))
              .map(r -> (GrantedAuthority) r::trim).collect(Collectors.toList());
        } else {
          grantedAuthorities = Collections.emptyList();
        }

        return Optional.of(
            new B2BAuthentication(new UserIdAuthentication(userId, grantedAuthorities)));
      } else {
        RequestInfoContextProvider.getInstance().getOrThrow()
            .setUserId(CoreConstants.SYSTEM_USER_ID);
      }

      return Optional.of(new B2BAuthentication());
    }
    return Optional.empty();
  }

  @Override
  public boolean containsContext(HttpServletRequest request) {
    return request.getHeader(Constants.Header.B2B_SECRET_HEADER) != null;
  }
}
