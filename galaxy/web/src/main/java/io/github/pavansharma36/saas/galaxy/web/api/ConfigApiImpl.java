package io.github.pavansharma36.saas.galaxy.web.api;

import io.github.pavansharma36.saas.core.dto.response.ListResponseObject;
import io.github.pavansharma36.saas.core.web.security.b2b.B2BGrantedAuthority;
import io.github.pavansharma36.saas.galaxy.api.ConfigApi;
import io.github.pavansharma36.saas.galaxy.common.dto.mapper.ConfigDTOMapper;
import io.github.pavansharma36.saas.galaxy.common.service.ConfigService;
import io.github.pavansharma36.saas.galaxy.dto.config.ConfigDto;
import io.github.pavansharma36.saas.galaxy.dto.config.ConfigValueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConfigApiImpl implements ConfigApi {

  private final ConfigService configService;

  @Override
  public ListResponseObject<ConfigDto> getConfigs() {
    return null;
  }

  @Override
  @Secured(B2BGrantedAuthority.ROLE_B2B)
  public ListResponseObject<ConfigValueDto> getConfigValues(String appName,
                                                            String appType) {
    return ListResponseObject.success(
        configService.getConfigValues(appName, appType).stream().map(ConfigDTOMapper::mapToValue)
            .toList()
    );
  }
}
