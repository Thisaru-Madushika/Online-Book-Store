package com.example.book_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
// Marks this class as a Spring Component so it is automatically registered as a filter
@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    // Reads the secret key from application.properties
    @Value("${api.key.secret}")
    private String apiKeySecret;

    // The name of the header we expect the client to send
    private static final String API_KEY_HEADER = "X-API-KEY";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Extract the API key from the incoming request header
        String requestApiKey = request.getHeader(API_KEY_HEADER);

        // Validate the API key
        if (requestApiKey == null || !requestApiKey.equals(apiKeySecret)) {
            // If invalid or missing, reject the request with a 401 Unauthorized status
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid or missing API Key");
            return;
        }

        // If valid, allow the request to proceed to the controller
        filterChain.doFilter(request, response);
    }

    // Ensure the filter only applies to API endpoints (skips things like Swagger UI later)
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return !path.startsWith("/api/");
    }
}