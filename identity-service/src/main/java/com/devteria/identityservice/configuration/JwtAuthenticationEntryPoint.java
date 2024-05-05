package com.devteria.identityservice.configuration;

import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(ErrorCode.UNAUTHENTICATED.getStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(ErrorCode.UNAUTHENTICATED.getCode())
                .message(ErrorCode.UNAUTHENTICATED.getMessage())
                .build();


        ObjectMapper objectMapper = new ObjectMapper(); //Doi tuong dung de bien object ve dang json string

        response.getWriter().write(objectMapper.writeValueAsString(apiResponse)); //su dung writer trong respone de viet chuoi json String
        response.flushBuffer(); //Force
    }
}
