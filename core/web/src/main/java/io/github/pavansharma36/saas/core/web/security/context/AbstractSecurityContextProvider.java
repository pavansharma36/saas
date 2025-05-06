package io.github.pavansharma36.saas.core.web.security.context;

import io.github.pavansharma36.core.common.context.providers.TenantContextProvider;
import io.github.pavansharma36.core.common.context.providers.UserContextProvider;
import io.github.pavansharma36.core.common.service.TenantService;
import io.github.pavansharma36.core.common.service.UserService;
import io.github.pavansharma36.saas.core.dto.common.TenantDto;
import io.github.pavansharma36.saas.core.dto.common.UserDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractSecurityContextProvider implements AppSecurityContextProvider {

  protected final TenantService tenantService;
  protected final UserService userService;

  protected void setTenantContext(String tenantId) {
    TenantDto tenantDto = tenantService.getTenantById(tenantId);
    TenantContextProvider.getInstance().set(tenantDto);
  }

  protected void setUserContext(String userId) {
    UserDto userDto = userService.getUserById(userId);
    UserContextProvider.getInstance().set(userDto);
  }

}
