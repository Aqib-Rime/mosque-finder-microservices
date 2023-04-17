package com.chotonakib.apigateway.config;

import com.chotonakib.apigateway.filters.ServerAuthFilter;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GatewayConfig {
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public ServerAuthFilter authenticationFilter(WebClient.Builder webClientBuilder) {
        return new ServerAuthFilter(webClientBuilder);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, RedisRateLimiter redisRateLimiter, ServerAuthFilter serverAuthFilter) {
        return builder.routes()
                .route(r -> r.path("/**")
                        .filters(f -> f.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter))
                                .filter(serverAuthFilter))
                        .uri("http://localhost:8081"))
                .build();
    }
}