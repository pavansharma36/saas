package io.github.pavansharma36.saas.core.web.filter;

import jakarta.servlet.annotation.WebFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * SpringSecurityFilter is just wrapper for annotation based config.
 */
@WebFilter(filterName = "springSecurityFilterChain", urlPatterns = "/api/*")
public class SpringSecurityFilter extends DelegatingFilterProxy {
}
