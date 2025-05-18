package io.github.pavansharma36.saas.galaxy.common.service;

import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.dao.ConfigDao;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model.Config;
import io.github.pavansharma36.saas.galaxy.common.utils.GalaxyConstants;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigService {

  private final ConfigDao configDAO;

  public Collection<Config> getConfigs() {
    return Collections.emptyList();
  }

  public Collection<Config> getConfigValues(String appName, String appType) {
    Map<String, String> criterias = getCriterias(appName, appType);

    Map<String, Config> confs = new HashMap<>();
    for (Map.Entry<String, String> criteria : criterias.entrySet()) {
      configDAO.selectByClassifier(criteria.getKey(), criteria.getValue())
          .forEach(c -> confs.put(c.getConfigName(), c));
    }
    return confs.values();
  }

  private Map<String, String> getCriterias(String appName, String appType) {
    Map<String, String> criterias = new HashMap<>();
    criterias.put(GalaxyConstants.CONFIG_CLASSIFIER_GLOBAL,
        GalaxyConstants.CONFIG_CLASSIFIER_GLOBAL);
    criterias.put(GalaxyConstants.CONFIG_CLASSIFIER_APPTYPE, appType);
    criterias.put(GalaxyConstants.CONFIG_CLASSIFIER_APPNAME, appName);
    criterias.put(GalaxyConstants.CONFIG_CLASSIFIER_APP_NAME_TYPE,
        String.format("%s-%s", appName, appType));
    return criterias;
  }

}
