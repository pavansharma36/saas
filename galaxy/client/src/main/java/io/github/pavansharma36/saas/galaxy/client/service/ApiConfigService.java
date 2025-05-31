package io.github.pavansharma36.saas.galaxy.client.service;

import io.github.pavansharma36.core.common.service.ConfigService;
import io.github.pavansharma36.saas.core.dto.common.ConfigValueDto;
import io.github.pavansharma36.saas.core.dto.response.ListResponseObject;
import io.github.pavansharma36.saas.galaxy.api.ConfigApi;
import io.github.pavansharma36.saas.galaxy.client.GalaxyClientFactory;
import io.github.pavansharma36.saas.utils.Enums;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ApiConfigService implements ConfigService {

  private final ConfigApi configApi = GalaxyClientFactory.configApi();

  @Override
  public List<ConfigValueDto> getConfigValues(String appName, Enums.AppType appType) {
    ListResponseObject<ConfigValueDto> res =
        configApi.getConfigValues(appName, appType.getName());
    return res.getData();
  }
}
