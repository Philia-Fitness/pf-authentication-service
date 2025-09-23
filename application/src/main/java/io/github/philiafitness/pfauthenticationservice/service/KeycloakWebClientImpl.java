package io.github.philiafitness.pfauthenticationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.philiafitness.pfauthenticationservice.keycloak.KeycloakBaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Primary
@Component
@RequiredArgsConstructor
@Slf4j
public class KeycloakWebClientImpl implements KeycloakWebClient {
    private final ObjectMapper objectMapper;
    private final WebClient keycloakWebClient;

    @Override
    public <T extends KeycloakBaseResponse> T doPost(String uri,
                                                     BodyInserters.FormInserter<String> bodyToUrlEncode,
                                                     Class<T> responseClass) {

        return keycloakWebClient.post()
                .uri(uri)
                .body(bodyToUrlEncode)
                .exchangeToMono(clientResponse -> clientResponse.statusCode().is2xxSuccessful()
                        ? clientResponse.bodyToMono(responseClass)
                        : clientResponse.createException().map(
                        e -> exceptionToBaseResponse(e, responseClass)))
                .block();
    }

    private <T extends KeycloakBaseResponse> T exceptionToBaseResponse(WebClientResponseException exception, Class<T> clazz) {
        try {
            return objectMapper.readValue(exception.getResponseBodyAsString(), clazz);
        } catch (JsonProcessingException e) {
            log.error("Cannot parse keycloak webclient error response: {}", exception.getResponseBodyAsString());
            throw new RuntimeException("Cannot parse keycloak webclient error response");
        }
    }
}
