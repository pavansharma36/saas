package io.github.pavansharma36.saas.auth.server.config;

import io.github.pavansharma36.saas.auth.common.config.AuthCommonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AuthCommonConfiguration.class, AuthWebSecurityConfig.class})
public class AuthServerConfiguration {

  @Bean
  public Boolean bool() {
    return true;
  }
}
