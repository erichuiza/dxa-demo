package com.alliancedata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import static com.sdl.webapp.common.util.InitializationUtils.loadActiveSpringProfiles;
import static com.sdl.webapp.common.util.InitializationUtils.registerListener;
import static com.sdl.webapp.common.util.InitializationUtils.registerServlet;

/**
 * Initializes the web application instead of <code>web.xml</code>.
 */
@Slf4j
public class WebAppInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();

        // register webapp spring configuration
        servletAppContext.register(SpringInitializer.class);

        registerListener(servletContext, new ContextLoaderListener(servletAppContext));

        registerServlet(servletContext, new DispatcherServlet(servletAppContext), "/").setLoadOnStartup(1);

        loadActiveSpringProfiles(servletContext, servletAppContext);
    }
}
