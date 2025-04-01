package io.github.pavansharma36.saas.core.api;

import io.github.pavansharma36.saas.core.dto.ResponseObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("open")
public interface CoreApi {

  @GetMapping("ping")
  ResponseObject<String> ping();

}
