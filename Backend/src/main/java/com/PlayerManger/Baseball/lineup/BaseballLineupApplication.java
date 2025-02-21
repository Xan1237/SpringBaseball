package com.PlayerManger.Baseball.lineup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BaseballLineupApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseballLineupApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // ✅ Allow all endpoints
						.allowedOrigins("http://localhost:4200") // ✅ Allow Angular frontend
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // ✅ Allow specific HTTP methods
						.allowedHeaders("*") // ✅ Allow all headers
						.allowCredentials(true); // ✅ Allow sending cookies (if needed)
			}
		};
	}
}
