package com.example.quartzimpl;


import jakarta.servlet.Servlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "spring.h2.console", name = "enabled", havingValue = "true")
public class H2ConsoleConfig {

    @Bean
    public ServletRegistrationBean<Servlet> h2Console() {
        try {
            // Try JakartaWebServlet first (H2 2.3+)
            Class<?> servletClass = Class.forName("org.h2.server.web.JakartaWebServlet");
            Servlet servlet = (Servlet) servletClass.getDeclaredConstructor().newInstance();

            ServletRegistrationBean<Servlet> registration = new ServletRegistrationBean<>(servlet);
            registration.addUrlMappings("/h2-console/*");
            registration.setLoadOnStartup(1);
            return registration;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("H2 JakartaWebServlet not found. Please use H2 version 2.3.232 or higher", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create H2 Console servlet", e);
        }
    }
}