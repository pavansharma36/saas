package io.github.pavansharma36.saas.galaxy.api;

import io.github.pavansharma36.saas.core.dto.ListResponseObject;
import io.github.pavansharma36.saas.core.dto.ResponseObject;
import io.github.pavansharma36.saas.galaxy.dto.config.ConfigValueDTO;
import io.github.pavansharma36.saas.utils.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("config")
public interface ConfigApi {

  /**
   * for testing.
   *
   * @param key
   * @return
   */
  @GetMapping("value")
  ResponseObject<ConfigValueDTO> getValue(@RequestParam("key") String key);

  @GetMapping
  ListResponseObject<ConfigValueDTO> getAll(
      @RequestHeader(Constants.APP_NAME_HEADER) String appName,
      @RequestHeader(Constants.APP_TYPE_HEADER) String appType);

}
