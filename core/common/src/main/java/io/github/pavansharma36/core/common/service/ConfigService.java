package io.github.pavansharma36.core.common.service;

import io.github.pavansharma36.saas.core.dto.common.ConfigValueDto;
import io.github.pavansharma36.saas.utils.Enums;
import java.util.List;

public interface ConfigService {
  List<ConfigValueDto> getConfigValues(String appName, Enums.AppType appType);
}
