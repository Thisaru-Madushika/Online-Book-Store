package com.example.api_gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            // Disable CSRF for stateless REST APIs
            .csrf(csrf -> csrf.disable())
            
            // Configure authorization rules
            .authorizeExchange(exchanges -> exchanges
                // Allow unauthenticated access to Book Service (secured by API Key instead)
                .pathMatchers("/api/books/**").permitAll() 
                // Require authentication for all other requests
                .anyExchange().authenticated()
            )
            
            // Enable OAuth 2.0 Resource Server support with JWT
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtDecoder(jwtDecoder())));
        
        return http.build();
    }

    // Provide a dummy JWT decoder to allow application startup without an active Identity Provider
    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return token -> Mono.empty(); 
    }
}