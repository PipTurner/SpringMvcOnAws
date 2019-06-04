package org.tutorial.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MvcWebApplicationInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Load database and spring security configurations
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppConfig.class, WebSecurityConfig.class };
    }

    // Load spring web configuration
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}