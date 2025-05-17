package com.example.MovieWebsite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Áp dụng cho tất cả các đường dẫn
                .allowedOrigins("http://localhost:3000", "https://moviewebda.site", "https://dreamy-dolphin-b51e61.netlify.app")
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Các phương thức HTTP cho phép
                .allowedHeaders("*")  // Cho phép tất cả các header
                .allowCredentials(true);  // Cho phép gửi cookies hoặc thông tin xác thực
    }
}

