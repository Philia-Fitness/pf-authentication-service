package io.github.philiafitness.pfauthenticationservice.service;

import io.github.philiafitness.pfauthenticationservice.keycloak.KeycloakLoginResponse;
import io.github.philiafitness.pfauthenticationservice.mapper.KeycloakResponseMapper;
import io.github.philiafitness.pfauthenticationservice.properties.KeycloakConfigurationProperties;
import io.github.philiafitness.pfauthenticationservicedto.request.LoginRequestBean;
import io.github.philiafitness.pfauthenticationservicedto.response.LoginResponseBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;

@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class KeycloakLoginGatewayWebClientImpl implements KeycloakLoginGatewayWebClient {
    private final KeycloakConfigurationProperties keycloakConfigurationProperties;
    private final KeycloakWebClient keycloakWebClient;
    private final KeycloakResponseMapper mapper;

    @Override
    @SuppressWarnings("unchecked")
    public LoginResponseBean requestToken(LoginRequestBean serviceRequest) {
        BodyInserters.FormInserter<String> body = BodyInserters
                .fromFormData("client_id", keycloakConfigurationProperties.getClient().getId())
                .with("client_secret", keycloakConfigurationProperties.getClient().getSecret())
                .with("grant_type", "password")
                .with("username", serviceRequest.getUsername())
                .with("password", serviceRequest.getPassword());

        KeycloakLoginResponse keycloakResponse = keycloakWebClient.doPost(keycloakConfigurationProperties.getEndpoints().getAuthentication(),
                body,
                KeycloakLoginResponse.class);

        return mapper.convertKeycloakLoginToServiceResponse(keycloakResponse);
    }
}
