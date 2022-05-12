package com.insurance.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// TODO: Auto-generated Javadoc
/**
 * The Class CorsConfiguration.
 */
@Configuration
public class CorsConfiguration {

    /** The Constant GET. */
    private static final String GET = "GET";
    
    /** The Constant POST. */
    private static final String POST = "POST";
    
    /** The Constant PUT. */
    private static final String PUT = "PUT";
    
    /** The Constant DELETE. */
    private static final String DELETE = "DELETE";

    /**
     * Cors configurer.
     *
     * @return the web mvc configurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods(GET, POST, PUT, DELETE)
                        .allowedHeaders("*")
                        .allowedOriginPatterns("*")
                        .allowCredentials(true);
            }
        };
    }
}
