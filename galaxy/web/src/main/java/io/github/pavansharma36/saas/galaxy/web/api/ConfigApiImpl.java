package io.github.pavansharma36.saas.galaxy.web.api;

import io.github.pavansharma36.core.common.context.providers.RequestInfoContextProvider;
import io.github.pavansharma36.saas.core.dto.ListResponseObject;
import io.github.pavansharma36.saas.core.web.security.b2b.B2BGrantedAuthority;
import io.github.pavansharma36.saas.galaxy.api.ConfigApi;
import io.github.pavansharma36.saas.galaxy.common.dto.mapper.ConfigDTOMapper;
import io.github.pavansharma36.saas.galaxy.common.service.ConfigService;
import io.github.pavansharma36.saas.galaxy.dto.config.ConfigValueDto;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConfigApiImpl implements ConfigApi {

  private final ConfigService configService;

  @Override
  @Secured(B2BGrantedAuthority.ROLE_B2B)
  public ListResponseObject<ConfigValueDto> getAll(String appName,
                                                   String appType) {
    if (RequestInfoContextProvider.getInstance().getOrThrow().getRetryAttempt() < 1) {
      throw new ServerRuntimeException("test");
    }
    return ListResponseObject.success(
        configService.getAllConfigs(appName, appType).stream().map(ConfigDTOMapper::mapToValue)
            .toList()
    );
  }
}
