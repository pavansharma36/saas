package io.github.pavansharma36.saas.galaxy.common.config;

import io.github.pavansharma36.saas.auth.client.config.AuthClientConfig;
import io.github.pavansharma36.saas.core.common.config.CoreConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("io.github.pavansharma36.saas.galaxy.common")
@Import({CoreConfiguration.class, AuthClientConfig.class})
public class GalaxyCommonConfiguration {

}
