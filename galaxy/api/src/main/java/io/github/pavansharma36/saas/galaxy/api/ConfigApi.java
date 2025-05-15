package io.github.pavansharma36.saas.galaxy.api;

import io.github.pavansharma36.saas.core.dto.query.PageableQuery;
import io.github.pavansharma36.saas.core.dto.response.ListResponseObject;
import io.github.pavansharma36.saas.galaxy.dto.config.ConfigDto;
import io.github.pavansharma36.saas.galaxy.dto.config.ConfigValueDto;
import io.github.pavansharma36.saas.utils.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("config")
public interface ConfigApi {

  @GetMapping
  ListResponseObject<ConfigDto> getConfigs(PageableQuery pageable);

  @GetMapping("value")
  ListResponseObject<ConfigValueDto> getConfigValues(
      @RequestHeader(Constants.Header.APP_NAME_HEADER) String appName,
      @RequestHeader(Constants.Header.APP_TYPE_HEADER) String appType);
}
