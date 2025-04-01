package io.github.pavansharma36.saas.core.server.config;

import io.github.pavansharma36.saas.utils.json.JsonUtils;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("io.github.pavansharma36.saas.core.server.api")
@EnableMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public abstract class AbstractWebConfig implements WebMvcConfigurer {

  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    WebMvcConfigurer.super.extendMessageConverters(converters);
    converters.removeIf(MappingJackson2HttpMessageConverter.class::isInstance);
    converters.add(new MappingJackson2HttpMessageConverter(JsonUtils.mapper()));
  }

}
