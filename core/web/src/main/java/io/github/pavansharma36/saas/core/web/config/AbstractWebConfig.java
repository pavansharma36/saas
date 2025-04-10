package io.github.pavansharma36.saas.core.web.config;

import io.github.pavansharma36.core.common.validation.AppValidatorFactory;
import io.github.pavansharma36.saas.core.web.api.CoreApiRequestInterceptor;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("io.github.pavansharma36.saas.core.web.api")
@EnableMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public abstract class AbstractWebConfig implements WebMvcConfigurer {

  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    WebMvcConfigurer.super.extendMessageConverters(converters);
    converters.removeIf(MappingJackson2HttpMessageConverter.class::isInstance);
    converters.add(new MappingJackson2HttpMessageConverter(JsonUtils.mapper()));
  }

  @Override
  public Validator getValidator() {
    return new SpringValidatorAdapter(AppValidatorFactory.getInstance().getValidator());
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    WebMvcConfigurer.super.addInterceptors(registry);
    registry.addInterceptor(new CoreApiRequestInterceptor());
  }
}
