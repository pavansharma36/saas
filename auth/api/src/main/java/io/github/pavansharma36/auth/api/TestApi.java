package io.github.pavansharma36.auth.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/get")
public interface TestApi {

  @GetMapping
  String test();

}
