package io.github.pavansharma36.saas.auth.client.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"io.github.pavansharma36.auth.api", "io.github.pavansharma36.saas.auth.client"})
public class AuthClientConfig {
}
