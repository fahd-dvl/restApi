package com.demo.restapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")                     // apply to all /api/... endpoints
                .allowedOrigins(
                        "https://www.google.com"     // admin panel if any
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")                       // usually fine
                .allowCredentials(true)                    // important if you use cookies / auth headers
                .maxAge(3600);                             // cache preflight for 1 hour
    }

}
