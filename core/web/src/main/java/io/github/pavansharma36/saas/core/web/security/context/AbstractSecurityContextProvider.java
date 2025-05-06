package io.github.pavansharma36.saas.core.web.security.context;

import io.github.pavansharma36.core.common.context.providers.UserContextProvider;
import io.github.pavansharma36.core.common.service.TenantService;
import io.github.pavansharma36.core.common.service.UserService;
import io.github.pavansharma36.saas.core.dto.common.UserDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractSecurityContextProvider implements AppSecurityContextProvider {
  
  protected final TenantService tenantService;
  protected final UserService userService;

  protected void setSystemUserContext() {
    UserDto dto = new UserDto();
    dto.setId("system");
    dto.setFirstName("system");
    dto.setLastName("system");
    dto.setEnabled(true);
    UserContextProvider.getInstance().set(dto);
  }
}
