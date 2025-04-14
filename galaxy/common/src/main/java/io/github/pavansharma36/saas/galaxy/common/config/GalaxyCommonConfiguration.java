package io.github.pavansharma36.saas.galaxy.common.config;

import io.github.pavansharma36.core.common.config.CoreCommonConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("io.github.pavansharma36.saas.galaxy.common")
@Import(CoreCommonConfiguration.class)
public class GalaxyCommonConfiguration {

}
