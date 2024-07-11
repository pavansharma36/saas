package io.github.pavansharma36.saas.galaxy.server.config;

import io.github.pavansharma36.saas.core.server.security.config.WebSecurityConfig;
import io.github.pavansharma36.saas.galaxy.common.config.GalaxyCommonConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({WebSecurityConfig.class, GalaxyCommonConfiguration.class})
public class GalaxyServerConfiguration {

}
