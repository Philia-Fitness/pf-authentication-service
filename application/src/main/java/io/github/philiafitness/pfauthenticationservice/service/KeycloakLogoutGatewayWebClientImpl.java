package io.github.philiafitness.pfauthenticationservice.service;

import io.github.philiafitness.pfauthenticationservice.keycloak.KeycloakBaseResponse;
import io.github.philiafitness.pfauthenticationservice.mapper.KeycloakResponseMapper;
import io.github.philiafitness.pfauthenticationservice.properties.KeycloakConfigurationProperties;
import io.github.philiafitness.pfauthenticationservicedto.request.LogoutRequestBean;
import io.github.philiafitness.pfstarter.pfwebstarter.bean.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;

import static java.util.Objects.isNull;

@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class KeycloakLogoutGatewayWebClientImpl implements KeycloakLogoutGatewayWebClient {

    private final KeycloakConfigurationProperties keycloakConfigurationProperties;
    private final KeycloakWebClient keycloakWebClient;
    private final KeycloakResponseMapper mapper;

    @Override
    @SuppressWarnings("unchecked")
    public BaseResponse requestLogout(LogoutRequestBean serviceRequest) {
        BodyInserters.FormInserter<String> body = BodyInserters
                .fromFormData("client_id", keycloakConfigurationProperties.getClient().getId())
                .with("client_secret", keycloakConfigurationProperties.getClient().getSecret())
                .with("refresh_token", serviceRequest.getRefreshToken());

        KeycloakBaseResponse keycloakResponse = keycloakWebClient.doPost(keycloakConfigurationProperties.getEndpoints().getLogout(),
                body,
                KeycloakBaseResponse.class);

        //204 no content success response
        if (isNull(keycloakResponse))
            keycloakResponse = KeycloakBaseResponse.builder().build();

        return mapper.convertKeycloakLogoutToServiceResponse(keycloakResponse);
    }
}
