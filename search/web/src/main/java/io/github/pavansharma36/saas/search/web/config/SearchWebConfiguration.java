package io.github.pavansharma36.saas.search.web.config;

import io.github.pavansharma36.saas.core.web.config.AbstractWebConfig;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("io.github.pavansharma36.saas.search.web.api")
public class SearchWebConfiguration extends AbstractWebConfig {
}
