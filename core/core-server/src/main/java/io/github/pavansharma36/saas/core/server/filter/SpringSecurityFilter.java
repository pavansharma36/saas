package io.github.pavansharma36.saas.core.server.filter;

import jakarta.servlet.annotation.WebFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

@WebFilter(filterName = "springSecurityFilterChain", urlPatterns = "/api/*")
public class SpringSecurityFilter extends DelegatingFilterProxy {
}
