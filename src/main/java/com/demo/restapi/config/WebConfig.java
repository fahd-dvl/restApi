package com.demo.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                // 1. Enable the "user-driven" query parameter approach
                .favorParameter(true)
                .parameterName("q")

                // 2. Do not ignore the Accept header (required for coefficients)
                .ignoreAcceptHeader(false)

                // 3. Set default format if nothing is specified
                .defaultContentType(MediaType.APPLICATION_JSON)

                // 4. Map the 'q' values to actual Media Types
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Bean
    public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }
}
