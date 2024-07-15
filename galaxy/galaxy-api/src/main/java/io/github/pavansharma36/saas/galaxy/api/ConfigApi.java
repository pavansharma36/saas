package io.github.pavansharma36.saas.galaxy.api;

import io.github.pavansharma36.saas.core.dto.ListResponseObject;
import io.github.pavansharma36.saas.galaxy.dto.config.ConfigValueDTO;
import io.github.pavansharma36.saas.utils.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("config")
public interface ConfigApi {
  @GetMapping
  ListResponseObject<ConfigValueDTO> getAll(
      @RequestHeader(Constants.APP_NAME_HEADER) String appName,
      @RequestHeader(Constants.APP_TYPE_HEADER) String appType);
}
