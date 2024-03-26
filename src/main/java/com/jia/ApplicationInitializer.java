package com.jia;

import com.jia.security.config.SecurityConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * This class implements the WebApplicationInitializer, an Interface for Servlet 3.0+ environments in order to configure
 * the ServletContext programmatically, as opposed to (or possibly in conjunctionwith) the traditional web.xml-based
 * approach.
 *
 * Implementations of this SPI will be detected automatically by SpringServletContainerInitializer, which itself is
 * bootstrapped automatically by any Servlet 3.0 container.
 */
public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(SecurityConfig.class);

        servletContext.addListener(new ContextLoaderListener(appContext));

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher",
                new DispatcherServlet(appContext));

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        servletContext.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");
    }
}
