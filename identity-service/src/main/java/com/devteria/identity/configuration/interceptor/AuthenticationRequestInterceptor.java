package com.devteria.identity.configuration.interceptor;
/*
 * Class nay se co nhiem vu Authentication co moi request
 *
 *
 * */

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthenticationRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        /*Get token then to call other micro-service*/
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        String authHeader = servletRequestAttributes.getRequest().getHeader("Authorization");
        log.info("Headear : {}", authHeader);

        if (StringUtils.hasText(authHeader)) template.header("Authorization", authHeader);
    }
}
