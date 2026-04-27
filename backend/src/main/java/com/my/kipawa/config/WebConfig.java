package com.my.kipawa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Used in Media Module
        // to serve uploaded files directly via URL
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }

}
