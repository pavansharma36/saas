package io.github.pavansharma36.saas.auth.server.config;

import io.github.pavansharma36.saas.core.server.security.config.WebSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(WebSecurityConfig.class)
public class AuthServerConfiguration {

  @Bean
  public Boolean bool() {
    return true;
  }
}
