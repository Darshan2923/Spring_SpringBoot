// package com.stream.app.wizstream.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class CorsConfig implements WebMvcConfigurer {

// @Override
// public void addCorsMappings(CorsRegistry registry) {
// registry.addMapping("/api/**") // Adjust this path as necessary
// .allowedOrigins("http://localhost:5173") // Frontend URL
// .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
// .allowedHeaders("*")
// .allowCredentials(true);
// }
// }