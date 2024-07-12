package io.github.pavansharma36.saas.galaxy.server.api;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.core.dto.ListResponseObject;
import io.github.pavansharma36.saas.core.dto.ResponseObject;
import io.github.pavansharma36.saas.core.server.security.b2b.B2BGrantedAuthority;
import io.github.pavansharma36.saas.galaxy.api.ConfigApi;
import io.github.pavansharma36.saas.galaxy.common.dto.mapper.ConfigDTOMapper;
import io.github.pavansharma36.saas.galaxy.common.service.ConfigService;
import io.github.pavansharma36.saas.galaxy.dto.config.ConfigValueDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConfigApiImpl implements ConfigApi {

  private final ConfigService configService;

  @Override
  @Secured(B2BGrantedAuthority.ROLE_B2B)
  public ResponseObject<ConfigValueDTO> getValue(String key) {
    return ResponseObject.<ConfigValueDTO>builder()
        .data(new ConfigValueDTO(key, Config.get(key)))
        .build();
  }

  @Override
  public ListResponseObject<ConfigValueDTO> getAll(String appName,
                                                   String appType) {
    return ListResponseObject.success(
        configService.getAllConfigs(appName, appType).stream().map(ConfigDTOMapper::mapToValue)
            .toList()
    );
  }
}
