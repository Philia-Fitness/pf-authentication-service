package io.github.philiafitness.pfauthenticationservice.service;

import io.github.philiafitness.pfauthenticationservice.keycloak.KeycloakRefreshResponse;
import io.github.philiafitness.pfauthenticationservice.mapper.KeycloakResponseMapper;
import io.github.philiafitness.pfauthenticationservice.properties.KeycloakConfigurationProperties;
import io.github.philiafitness.pfauthenticationservicedto.request.RefreshRequestBean;
import io.github.philiafitness.pfauthenticationservicedto.response.RefreshResponseBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;

@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class KeycloakRefreshGatewayWebClientImpl implements KeycloakRefreshGatewayWebClient {
    private final KeycloakConfigurationProperties keycloakConfigurationProperties;
    private final KeycloakWebClient keycloakWebClient;
    private final KeycloakResponseMapper mapper;

    @Override
    @SuppressWarnings("unchecked")
    public RefreshResponseBean requestToken(RefreshRequestBean serviceRequest) {
        BodyInserters.FormInserter<String> body = BodyInserters
                .fromFormData("client_id", keycloakConfigurationProperties.getClient().getId())
                .with("client_secret", keycloakConfigurationProperties.getClient().getSecret())
                .with("grant_type", "refresh_token")
                .with("refresh_token", serviceRequest.getRefreshToken());

        KeycloakRefreshResponse keycloakResponse = keycloakWebClient.doPost(keycloakConfigurationProperties.getEndpoints().getRefreshToken(), body, KeycloakRefreshResponse.class);

        return mapper.convertKeycloakRefreshToServiceResponse(keycloakResponse);
    }
}
