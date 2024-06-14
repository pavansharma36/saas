package io.github.pavansharma36.saas.auth.server.config;

import io.github.pavansharma36.saas.core.server.config.AbstractWebConfig;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"io.github.pavansharma36.saas.auth.server.api"})
public class AuthServerWebConfiguration extends AbstractWebConfig {
}
