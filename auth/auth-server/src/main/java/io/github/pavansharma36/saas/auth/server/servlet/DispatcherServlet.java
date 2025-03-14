package io.github.pavansharma36.saas.auth.server.servlet;

import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(urlPatterns = "/api/*", initParams = {
    @WebInitParam(name = "contextClass", value = "org.springframework.web.context.support.AnnotationConfigWebApplicationContext"),
    @WebInitParam(name = "contextConfigLocation",
        value = "io.github.pavansharma36.saas.auth.server.config.AuthServerWebConfiguration")
}, loadOnStartup = 1, asyncSupported = true)
public class DispatcherServlet extends org.springframework.web.servlet.DispatcherServlet {

}
