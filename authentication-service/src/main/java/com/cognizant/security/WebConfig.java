package com.cognizant.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebFluxConfigurer {

public void addCorsMappings(CorsRegistry registry) {
registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
}

}
