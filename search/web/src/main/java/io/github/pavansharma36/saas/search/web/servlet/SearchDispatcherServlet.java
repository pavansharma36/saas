package io.github.pavansharma36.saas.search.web.servlet;

import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.DispatcherServlet;

@Slf4j
@WebServlet(urlPatterns = "/api/*", initParams = {
    @WebInitParam(name = "contextClass", value = "org.springframework.web.context.support.AnnotationConfigWebApplicationContext"),
    @WebInitParam(name = "contextConfigLocation",
        value = "io.github.pavansharma36.saas.search.web.config.SearchWebConfiguration")
}, loadOnStartup = 1, asyncSupported = true)
public class SearchDispatcherServlet extends DispatcherServlet {
}
