package io.github.pavansharma36.saas.galaxy.api;

import io.github.pavansharma36.saas.core.dto.ListResponseObject;
import io.github.pavansharma36.saas.galaxy.dto.config.ConfigValueDto;
import io.github.pavansharma36.saas.utils.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("config")
public interface ConfigApi {
  @GetMapping
  ListResponseObject<ConfigValueDto> getAll(
      @RequestHeader(Constants.Header.APP_NAME_HEADER) String appName,
      @RequestHeader(Constants.Header.APP_TYPE_HEADER) String appType);
}
