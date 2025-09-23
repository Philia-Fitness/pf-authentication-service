package io.github.philiafitness.pfauthenticationservice.service;

import io.github.philiafitness.pfauthenticationservice.keycloak.KeycloakBaseResponse;
import org.springframework.web.reactive.function.BodyInserters;

public interface KeycloakWebClient {
    <T extends KeycloakBaseResponse> T doPost(String uri,
                                              BodyInserters.FormInserter<String> bodyToUrlEncode,
                                              Class<T> responseClass);
}
