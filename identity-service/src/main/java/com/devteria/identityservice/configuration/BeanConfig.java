package com.devteria.identityservice.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {

    /*remove bean because customJwt use 1 authenticationService, 1 authenticationService use 1 bean PasswordEndCoder, Khi tao Security Config can customJwt, va can PasswordEncoder
    * ==> dan toi vong trong lap bean, can tach PasswordEndcoder thanh 1 bean riengbiet
    * */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
