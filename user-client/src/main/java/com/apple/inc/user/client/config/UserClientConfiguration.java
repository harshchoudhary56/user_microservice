package com.apple.inc.user.client.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class for external clients
 * Other services importing this client library will use this configuration
 */
@Configuration
@EnableFeignClients(basePackages = "com.apple.inc.user.client")
public class UserClientConfiguration {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
