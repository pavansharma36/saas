package io.github.pavansharma36.saas.core.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableWebMvc
@EnableMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public abstract class AbstractWebConfig extends WebMvcConfigurationSupport {



}
