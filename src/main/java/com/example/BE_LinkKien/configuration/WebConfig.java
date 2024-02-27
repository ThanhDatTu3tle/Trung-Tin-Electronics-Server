package com.example.BE_LinkKien.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://192.168.0.111:3000", "http://192.168.1.89:3000", "http://localhost:3000", "https://trung-tin-electronics-client.vercel.app")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("Authorization") // Nếu bạn muốn bổ sung thêm header cho response
                .allowCredentials(true);
    }
}
