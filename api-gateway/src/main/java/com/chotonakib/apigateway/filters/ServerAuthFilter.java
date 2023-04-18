package com.chotonakib.apigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ServerAuthFilter implements GatewayFilter {
    private final WebClient webClient;

    public ServerAuthFilter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // get the access token from the request headers
        String accessToken = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        // make the authentication request to another server
        return webClient.get()
                .uri("http://localhost:8083/auth?accessToken=" + accessToken)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        // if response, pass the request to the next filter in the chain
                        ServerHttpRequest newRequest = exchange.getRequest().mutate().headers(httpHeaders -> httpHeaders.remove(HttpHeaders.AUTHORIZATION)).build();
                        return chain.filter(exchange.mutate().request(newRequest).build());
                    } else {
                        // if not response, return a 401 Unauthorized response
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    }
                });
    }
}
