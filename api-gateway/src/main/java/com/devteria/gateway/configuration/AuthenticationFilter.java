package com.devteria.gateway.configuration;


import com.devteria.gateway.service.IdentiyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationFilter implements GlobalFilter, Ordered {

    IdentiyService identiyService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Enter authentication filter");
        
        /*get token from authorization header     */
        List<String> authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
        if(CollectionUtils.isEmpty(authHeaders))
            return unAuthenticated(exchange.getResponse());

        String token= authHeaders.getFirst().replace("Bearer ","");
        log.info("Token: {}", token);

        /*verify token 
         * delegate identity service with api instropect token.
         * return Api response with 401
         */
        identiyService.introspect(token).subscribe(introspectResponseApiResponse -> {
            log.info("Result: {}", introspectResponseApiResponse.getResult().isValid());
        });

        
        
        return chain.filter((exchange));
    }

    @Override
    public int getOrder() {
        return -1;
        //return thu tu uu tien cua filter
    }

    private Mono<Void> unAuthenticated(ServerHttpResponse response){
        String body = "Unauthenticated1";
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(body.getBytes())));
    }
}
