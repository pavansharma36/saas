package io.github.pavansharma36.saas.auth.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AuthWebSecurityConfig.class)
public class AuthServerConfiguration {

  @Bean
  public Boolean bool() {
    return true;
  }
}
