package io.github.pavansharma36.saas.galaxy.web.config;

import io.github.pavansharma36.saas.core.web.config.AbstractWebConfig;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"io.github.pavansharma36.saas.galaxy.web.api"})
public class GalaxyWebConfiguration extends AbstractWebConfig {
}
