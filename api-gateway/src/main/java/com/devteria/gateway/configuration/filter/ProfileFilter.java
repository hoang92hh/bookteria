package com.devteria.gateway.configuration.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.devteria.gateway.configuration.AuthenticationFilter;
import com.devteria.gateway.service.IdentiyService;
import com.devteria.gateway.service.ProfileGatewayService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;




@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileFilter implements GlobalFilter, Ordered {
	
	ProfileGatewayService profileGatewayService;

	@Override
	public int getOrder() {		
		return 0;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		log.info("Chay vao ProfileFilter");
	
		var result = profileGatewayService.getUserProfileList();
		
		log.info(result.toString());
		
		return chain.filter(exchange);
	}

}
