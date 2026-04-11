package com.apple.inc.user.client;

import com.apple.inc.user.dto.FieldParam;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * WebClient-based service for reactive communication with user-service
 * Alternative to Feign client for reactive programming
 */
@Service
public class UserWebClient {

    private final WebClient webClient;

    public UserWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://user-service")
                .build();
    }

    public Mono<String> sampleMethod() {
        return webClient.get()
                .uri("/user/")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<FieldParam> getUserById(Long id) {
        return webClient.get()
                .uri("/user/{id}", id)
                .retrieve()
                .bodyToMono(FieldParam.class);
    }

    public Mono<FieldParam> createUser(FieldParam userRequest) {
        return webClient.post()
                .uri("/user/")
                .bodyValue(userRequest)
                .retrieve()
                .bodyToMono(FieldParam.class);
    }

    public Mono<FieldParam> updateUser(Long id, FieldParam userRequest) {
        return webClient.put()
                .uri("/user/{id}", id)
                .bodyValue(userRequest)
                .retrieve()
                .bodyToMono(FieldParam.class);
    }

    public Mono<Void> deleteUser(Long id) {
        return webClient.delete()
                .uri("/user/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Flux<FieldParam> searchUsers(String name, String email) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/user/search")
                        .queryParamIfPresent("name", java.util.Optional.ofNullable(name))
                        .queryParamIfPresent("email", java.util.Optional.ofNullable(email))
                        .build())
                .retrieve()
                .bodyToFlux(FieldParam.class);
    }
}
