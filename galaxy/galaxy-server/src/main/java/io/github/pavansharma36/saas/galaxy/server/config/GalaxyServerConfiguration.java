package io.github.pavansharma36.saas.galaxy.server.config;

import io.github.pavansharma36.saas.core.server.security.config.WebSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(WebSecurityConfig.class)
public class GalaxyServerConfiguration {

}
