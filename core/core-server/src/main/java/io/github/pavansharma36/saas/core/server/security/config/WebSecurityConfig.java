package io.github.pavansharma36.saas.core.server.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AnonymousConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, proxyTargetClass = true)
@ComponentScan("io.github.pavansharma36.saas.core.server.security")
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http,
                                         AuthenticationManager authenticationManager,
                                         AccessDeniedHandler accessDeniedHandler,
                                         AuthenticationEntryPoint authenticationEntryPoint,
                                         SecurityContextRepository securityContextRepository)
      throws Exception {
    http
        .authorizeHttpRequests(requests -> requests.requestMatchers(
                new AntPathRequestMatcher("/api/open/**")).permitAll()
            .anyRequest().fullyAuthenticated()
        ).logout(LogoutConfigurer::permitAll)
        .csrf(CsrfConfigurer::disable)
        .logout(AbstractHttpConfigurer::disable)
        .sessionManagement(SessionManagementConfigurer::disable)
        .anonymous(AnonymousConfigurer::disable)
        .requestCache(RequestCacheConfigurer::disable)
        .exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(authenticationEntryPoint))
        .securityContext(
            s -> s.securityContextRepository(securityContextRepository));

    customize(http, authenticationManager);
    return http.getOrBuild();
  }

  protected void customize(HttpSecurity http, AuthenticationManager authenticationManager) {
    // NOTHING in base.
  }

}
