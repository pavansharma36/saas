package io.github.pavansharma36.saas.galaxy.web.api;

import io.github.pavansharma36.saas.core.dto.common.ConfigValueDto;
import io.github.pavansharma36.saas.core.dto.query.PageableQuery;
import io.github.pavansharma36.saas.core.dto.response.ListResponseObject;
import io.github.pavansharma36.saas.core.web.security.b2b.B2BGrantedAuthority;
import io.github.pavansharma36.saas.galaxy.api.ConfigApi;
import io.github.pavansharma36.saas.galaxy.common.service.GalaxyConfigService;
import io.github.pavansharma36.saas.galaxy.dto.config.ConfigDto;
import io.github.pavansharma36.saas.utils.Enums;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConfigApiImpl implements ConfigApi {

  private final GalaxyConfigService galaxyConfigService;

  @Override
  public ListResponseObject<ConfigDto> getConfigs(@Valid PageableQuery pageableQuery) {
    return ListResponseObject.<ConfigDto>builder().build();
  }

  @Override
  @Secured(B2BGrantedAuthority.ROLE_B2B)
  public ListResponseObject<ConfigValueDto> getConfigValues(String appName,
                                                            String appType) {
    return ListResponseObject.success(
        galaxyConfigService.getConfigValues(appName, Enums.AppType.valueOf(appType))
    );
  }
}
