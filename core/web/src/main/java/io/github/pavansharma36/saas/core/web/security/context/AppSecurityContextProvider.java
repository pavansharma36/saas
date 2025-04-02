package io.github.pavansharma36.saas.core.web.security.context;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.context.HttpRequestResponseHolder;

public interface AppSecurityContextProvider {

  Optional<Authentication> authentication(HttpRequestResponseHolder requestResponseHolder);

  boolean containsContext(HttpServletRequest request);

}
