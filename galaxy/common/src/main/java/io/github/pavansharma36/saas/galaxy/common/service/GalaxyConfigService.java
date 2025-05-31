package io.github.pavansharma36.saas.galaxy.common.service;

import io.github.pavansharma36.core.common.service.ConfigService;
import io.github.pavansharma36.saas.core.dto.common.ConfigValueDto;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.dao.ConfigDao;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model.Config;
import io.github.pavansharma36.saas.galaxy.common.utils.GalaxyConstants;
import io.github.pavansharma36.saas.utils.Enums;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GalaxyConfigService implements ConfigService {

  private final ConfigDao configDAO;

  public Collection<Config> getConfigs() {
    return Collections.emptyList();
  }

  public List<ConfigValueDto> getConfigValues(String appName, Enums.AppType appType) {
    Map<String, String> criterias = getCriterias(appName, appType);

    Map<String, String> confs = new HashMap<>();
    for (Map.Entry<String, String> criteria : criterias.entrySet()) {
      configDAO.selectByClassifier(criteria.getKey(), criteria.getValue())
          .forEach(c -> confs.put(c.getConfigName(), c.getConfigValue()));
    }
    return confs.entrySet().stream().map(e -> new ConfigValueDto(e.getKey(), e.getValue()))
        .toList();
  }

  private Map<String, String> getCriterias(String appName, Enums.AppType appType) {
    Map<String, String> criterias = new HashMap<>();
    criterias.put(GalaxyConstants.CONFIG_CLASSIFIER_GLOBAL,
        GalaxyConstants.CONFIG_CLASSIFIER_GLOBAL);
    criterias.put(GalaxyConstants.CONFIG_CLASSIFIER_APPTYPE, appType.getName());
    criterias.put(GalaxyConstants.CONFIG_CLASSIFIER_APPNAME, appName);
    criterias.put(GalaxyConstants.CONFIG_CLASSIFIER_APP_NAME_TYPE,
        String.format("%s-%s", appName, appType));
    return criterias;
  }

}
