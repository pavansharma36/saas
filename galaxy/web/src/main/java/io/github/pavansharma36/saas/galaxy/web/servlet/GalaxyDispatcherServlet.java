package io.github.pavansharma36.saas.galaxy.web.servlet;

import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(urlPatterns = "/api/*", initParams = {
    @WebInitParam(name = "contextClass", value = "org.springframework.web.context.support.AnnotationConfigWebApplicationContext"),
    @WebInitParam(name = "contextConfigLocation",
        value = "io.github.pavansharma36.saas.galaxy.web.config.GalaxyWebConfiguration")
}, loadOnStartup = 1, asyncSupported = true)
public class GalaxyDispatcherServlet extends org.springframework.web.servlet.DispatcherServlet {

}
