package io.github.pavansharma36.saas.auth.common.config;

import io.github.pavansharma36.core.common.config.CoreCommonConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CoreCommonConfiguration.class)
@ComponentScan("io.github.pavansharma36.saas.auth.common")
public class AuthCommonConfiguration {

}
