package io.github.pavansharma36.saas.auth.server.api;

import io.github.pavansharma36.auth.api.TestApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiImpl implements TestApi {

  @Override
  public String test() {
    return "OK";
  }
}
