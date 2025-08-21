package io.github.pavansharma36.saas.auth.web.config;

import io.github.pavansharma36.saas.core.common.service.UserService;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.dao.UserAccountDao;
import io.github.pavansharma36.saas.auth.web.security.AppUserDetailsService;
import io.github.pavansharma36.saas.auth.web.security.RestAuthenticationProcessingFilter;
import io.github.pavansharma36.saas.core.web.security.config.WebSecurityConfig;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class AuthWebSecurityConfig extends WebSecurityConfig {

  private final UserAccountDao userAccountDao;
  private final UserService userService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  @Primary
  @Override
  public UserDetailsService userDetailsService() {
    return new AppUserDetailsService(userAccountDao, userService);
  }

  public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder,
                                                       UserDetailsService userDetailsService) {
    DaoAuthenticationProvider daoAuthenticationProvider =
        new DaoAuthenticationProvider(passwordEncoder);
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    return daoAuthenticationProvider;
  }

  public AuthenticationManager authenticationManager(
      AuthenticationProvider authenticationProvider) {
    return new ProviderManager(
        List.of(authenticationProvider, new RememberMeAuthenticationProvider("TODO-secret"))
    );
  }


  @Override
  protected void customize(HttpSecurity http) {
    super.customize(http);
    http.addFilterBefore(
        new RestAuthenticationProcessingFilter(
            authenticationManager(authenticationProvider(passwordEncoder(), userDetailsService())),
            null), UsernamePasswordAuthenticationFilter.class);
  }
}
