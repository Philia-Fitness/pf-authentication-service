package io.github.philiafitness.pfauthenticationservice.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum KeycloakErrorEnum {
    GENERIC_ERROR("generic_error"),
    INVALID_GRANT("invalid_grant"),
    INVALID_CLIENT("invalid_client"),
    UNAUTHORIZED_CLIENT("unauthorized_client"),
    INVALID_REQUEST("invalid_request"),
    UNSUPPORTED_GRANT_TYPE("unsupported_grant_type");

    private final String error;

    public static KeycloakErrorEnum enumFromError(String error) {
        Optional<KeycloakErrorEnum> first = Arrays.stream(KeycloakErrorEnum.values())
                .filter(e -> e.getError().equals(error))
                .findFirst();
        return first.orElse(GENERIC_ERROR);
    }


}
