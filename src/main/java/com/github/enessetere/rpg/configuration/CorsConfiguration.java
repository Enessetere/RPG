package com.github.enessetere.rpg.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowCredentials(false)
                .allowedHeaders("Access-Control-Allow-Origin", "Content-Type")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(3600);
    }
}
