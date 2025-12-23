package com.periodictable;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@OpenAPIDefinition(
    info = @Info(
        title = "Periodic table API",
        version = "1.0",
        description = "API for listing elements from the Periodic table"
    )
)
@SpringBootApplication
public class PeriodicTableMicroserviceApplication {

  @Value("${cors.frontend-address}")
  private String frontendAddress;

  public static void main(String[] args) {
    SpringApplication.run(PeriodicTableMicroserviceApplication.class, args);
  }

  @Bean
  CorsWebFilter corsWebFilter() {
    CorsConfiguration corsConfig = new CorsConfiguration();
    corsConfig.setAllowedOrigins(List.of(frontendAddress));
    corsConfig.setMaxAge(8000L);
    corsConfig.addAllowedMethod(HttpMethod.GET);
    corsConfig.addAllowedHeader(CorsConfiguration.ALL);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfig);

    return new CorsWebFilter(source);
  }

}
