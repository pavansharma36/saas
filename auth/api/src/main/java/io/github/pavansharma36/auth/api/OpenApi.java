package io.github.pavansharma36.auth.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/open")
public interface OpenApi {

  @GetMapping
  String get();

}
