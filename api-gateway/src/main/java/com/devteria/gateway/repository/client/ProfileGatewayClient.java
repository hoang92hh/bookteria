package com.devteria.gateway.repository.client;

import org.springframework.http.MediaType;
import org.springframework.web.service.annotation.PostExchange;

import com.devteria.gateway.dto.ApiResponse;
import com.devteria.gateway.dto.response.ProfileResponse;

import reactor.core.publisher.Mono;

public interface ProfileGatewayClient {
	
	@PostExchange(url= "/users", contentType = MediaType.APPLICATION_JSON_VALUE)
	Mono<ApiResponse<ProfileResponse>> getUserProfileList();

}
