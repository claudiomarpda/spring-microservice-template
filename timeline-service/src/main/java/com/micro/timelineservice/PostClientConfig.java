package com.micro.timelineservice;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostClientConfig {

    @Bean
    public BasicAuthRequestInterceptor postInterceptor(@Value("${timeline.client.post.user}") String user,
                                                       @Value("${timeline.client.post.password}") String password) {
        return new BasicAuthRequestInterceptor(user, password);
    }

}
