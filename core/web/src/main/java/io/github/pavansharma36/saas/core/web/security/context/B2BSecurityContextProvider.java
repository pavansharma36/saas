package io.github.pavansharma36.saas.core.web.security.context;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.core.common.context.providers.UserContextProvider;
import io.github.pavansharma36.core.common.crypto.CryptUtil;
import io.github.pavansharma36.core.common.crypto.KeyType;
import io.github.pavansharma36.core.common.service.TenantService;
import io.github.pavansharma36.core.common.service.UserService;
import io.github.pavansharma36.saas.core.web.security.b2b.B2BAuthentication;
import io.github.pavansharma36.saas.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.context.HttpRequestResponseHolder;

public class B2BSecurityContextProvider extends AbstractSecurityContextProvider
    implements AppSecurityContextProvider {

  private static final String B2B_HEADER_VALUE = Config.get(Constants.B2B_SECRET_CONF);

  private final List<AppSecurityContextProvider> appSecurityContextProviders;

  public B2BSecurityContextProvider(
      TenantService tenantService,
      UserService userService,
      List<AppSecurityContextProvider> appSecurityContextProviders) {
    super(tenantService, userService);
    this.appSecurityContextProviders = appSecurityContextProviders;
  }

  @Override
  public Optional<Authentication> authentication(HttpRequestResponseHolder requestResponseHolder) {
    String header =
        requestResponseHolder.getRequest().getHeader(Constants.Header.B2B_SECRET_HEADER);
    if (header != null &&
        B2B_HEADER_VALUE.equals(CryptUtil.decryptQuietly(KeyType.DEFAULT, header).orElse(null))) {
      for (AppSecurityContextProvider provider : appSecurityContextProviders) {
        Optional<Authentication> authentication =
            provider.authentication(requestResponseHolder);
        if (authentication.isPresent()) {
          return Optional.of(new B2BAuthentication(authentication.get()));
        }
      }
      String tenantId =
          requestResponseHolder.getRequest().getHeader(Constants.Header.TENANT_ID_HEADER);
      if (tenantId != null) {
        setTenantContext(tenantId);
      }

      String userId = requestResponseHolder.getRequest().getHeader(Constants.Header.USER_ID_HEADER);
      if (userId != null) {
        setUserContext(userId);
      } else {
        UserContextProvider.getInstance().set(userService.createSystemUser());
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
