//package com.example.quartzimpl;
//
//import jakarta.servlet.Servlet;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ConditionalOnProperty(name = "spring.h2.console.enabled", havingValue = "true")
//public class H2ConsoleConfiguration {
//
//    @Bean
//    public ServletRegistrationBean<Servlet> h2ServletRegistration() {
//        try {
//            // Try to load the H2 servlet class dynamically
//            Class<?> webServletClass = Class.forName("org.h2.server.web.WebServlet");
//            Servlet servlet = (Servlet) webServletClass.getDeclaredConstructor().newInstance();
//
//            ServletRegistrationBean<Servlet> registration =
//                    new ServletRegistrationBean<>(servlet);
//            registration.addUrlMappings("/h2-console/*");
//            registration.setLoadOnStartup(1);
//            return registration;
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to create H2 Console servlet", e);
//        }
//    }
//}