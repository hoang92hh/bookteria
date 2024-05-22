package com.devteria.gateway.service;

import org.springframework.stereotype.Service;

import com.devteria.gateway.dto.ApiResponse;
import com.devteria.gateway.dto.response.ProfileResponse;
import com.devteria.gateway.repository.IdentityClient;
import com.devteria.gateway.repository.client.ProfileGatewayClient;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE, makeFinal = true)
public class ProfileGatewayService {
	ProfileGatewayClient profileGatewayClient;	
	
	public Mono<ApiResponse<ProfileResponse>> getUserProfileList(){
		return profileGatewayClient.getUserProfileList();
	}
}
