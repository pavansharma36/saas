package io.github.pavansharma36.saas.search.web.config;

import io.github.pavansharma36.saas.core.web.security.config.WebSecurityConfig;
import io.github.pavansharma36.saas.search.common.config.SearchCommonConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("io.github.pavansharma36.saas.search.web")
@Import({SearchCommonConfiguration.class, WebSecurityConfig.class})
public class SearchConfiguration {
}
