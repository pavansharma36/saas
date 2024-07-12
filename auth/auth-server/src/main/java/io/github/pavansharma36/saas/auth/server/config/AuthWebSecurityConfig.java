package io.github.pavansharma36.saas.auth.server.config;

import io.github.pavansharma36.saas.auth.server.security.RestAuthenticationProcessingFilter;
import io.github.pavansharma36.saas.core.server.security.config.WebSecurityConfig;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthWebSecurityConfig extends WebSecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public RememberMeServices rememberMeServices() {
    return new NullRememberMeServices();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    // TODO
    User user =
        new User("user", "{noop}password", Collections.singleton(() -> "ROLE_USER"));
    return new InMemoryUserDetailsManager(Collections.singleton(user));
  }

  @Bean
  public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder,
                                                       UserDetailsService userDetailsService) {
    DaoAuthenticationProvider daoAuthenticationProvider =
        new DaoAuthenticationProvider(passwordEncoder);
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    return daoAuthenticationProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationProvider authenticationProvider) {
    return new ProviderManager(
        List.of(authenticationProvider, new RememberMeAuthenticationProvider("TODO-secret"))
    );
  }


  @Override
  protected void customize(HttpSecurity http, AuthenticationManager authenticationManager) {
    super.customize(http, authenticationManager);
    http.addFilterBefore(
        new RestAuthenticationProcessingFilter(authenticationManager, null, rememberMeServices()),
        UsernamePasswordAuthenticationFilter.class);
  }
}
