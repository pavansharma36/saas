package io.github.pavansharma36.saas.auth.web.api;

import io.github.pavansharma36.auth.api.OpenApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenApiImpl implements OpenApi {
  @Override
  public String get() {
    return "Open Success";
  }
}
