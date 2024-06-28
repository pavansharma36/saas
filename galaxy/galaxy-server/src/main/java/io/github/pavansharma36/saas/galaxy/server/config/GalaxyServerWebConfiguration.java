package io.github.pavansharma36.saas.galaxy.server.config;

import io.github.pavansharma36.saas.core.server.config.AbstractWebConfig;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"io.github.pavansharma36.saas.core.server.api",
    "io.github.pavansharma36.saas.galaxy.server.api"})
public class GalaxyServerWebConfiguration extends AbstractWebConfig {
}
