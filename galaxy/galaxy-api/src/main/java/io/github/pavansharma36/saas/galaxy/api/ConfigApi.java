package io.github.pavansharma36.saas.galaxy.api;

import io.github.pavansharma36.saas.core.dto.ResponseObject;
import io.github.pavansharma36.saas.galaxy.dto.config.ConfigValueDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("config")
public interface ConfigApi {

  @GetMapping("value")
  ResponseObject<ConfigValueDTO> getValue(@RequestParam("key") String key);

}
