package io.github.philiafitness.pfauthenticationservice.mapper;

import com.newhorizon.nhauthenticationsupportservice.dto.response.keycloak.KeycloakBaseResponse;
import com.newhorizon.nhauthenticationsupportservice.dto.response.keycloak.KeycloakLoginResponse;
import com.newhorizon.nhauthenticationsupportservice.dto.response.keycloak.KeycloakRefreshResponse;
import com.newhorizon.nhauthenticationsupportservice.enums.KeycloakErrorEnum;
import com.newhorizon.nhauthenticationsupportservicedto.monolith.BaseResponse;
import com.newhorizon.nhauthenticationsupportservicedto.monolith.EmptyResponseBean;
import com.newhorizon.nhauthenticationsupportservicedto.monolith.ResponseCodesEnum;
import com.newhorizon.nhauthenticationsupportservicedto.response.LoginResponseBean;
import com.newhorizon.nhauthenticationsupportservicedto.response.RefreshResponseBean;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
public interface KeycloakResponseMapper {
    @Mapping(target = "responseCode", source = "keycloakResponse", qualifiedByName = "setCode")
    @Mapping(target = "responseMessage", source = "keycloakResponse", qualifiedByName = "setMessage")
    @Mapping(target = "status", source = "keycloakResponse", qualifiedByName = "setStatus")
    LoginResponseBean convertKeycloakLoginToServiceResponse(KeycloakLoginResponse keycloakResponse);

    @Mapping(target = "responseCode", source = "keycloakResponse", qualifiedByName = "setCode")
    @Mapping(target = "responseMessage", source = "keycloakResponse", qualifiedByName = "setMessage")
    @Mapping(target = "status", source = "keycloakResponse", qualifiedByName = "setStatus")
    RefreshResponseBean convertKeycloakRefreshToServiceResponse(KeycloakRefreshResponse keycloakResponse);

    @Mapping(target = "responseCode", source = "keycloakResponse", qualifiedByName = "setCode")
    @Mapping(target = "responseMessage", source = "keycloakResponse", qualifiedByName = "setMessage")
    @Mapping(target = "status", source = "keycloakResponse", qualifiedByName = "setStatus")
    EmptyResponseBean convertKeycloakLogoutToServiceResponse(KeycloakBaseResponse keycloakResponse);

    @Named("setCode")
    default Integer setCode(KeycloakBaseResponse keycloakBaseResponse) {
        return errorConverter(keycloakBaseResponse).getErrorCode();

    }

    @Named("setMessage")
    default String setMessage(KeycloakBaseResponse keycloakBaseResponse) {
        return errorConverter(keycloakBaseResponse).getDescription();
    }

    @Named("setStatus")
    default BaseResponse.Status setStatus(KeycloakBaseResponse keycloakBaseResponse) {
        return errorConverter(keycloakBaseResponse).getStatus();
    }

    default ResponseCodesEnum errorConverter(KeycloakBaseResponse keycloakBaseResponse) {
        if (isNull(keycloakBaseResponse.getError())) {
            return ResponseCodesEnum.OK;
        }
        KeycloakErrorEnum keycloakErrorEnum = KeycloakErrorEnum.enumFromError(keycloakBaseResponse.getError());
        return switch (keycloakErrorEnum) {
            case GENERIC_ERROR -> ResponseCodesEnum.GENERIC_ERROR;
            case INVALID_GRANT -> ResponseCodesEnum.BAD_CREDENTIALS;
            case INVALID_CLIENT -> ResponseCodesEnum.INVALID_CLIENT_CONFIGURATION;
            case UNAUTHORIZED_CLIENT -> ResponseCodesEnum.UNHAUTORIZED_CLIENT;
            case INVALID_REQUEST -> ResponseCodesEnum.BAD_PARAMETER;
            case UNSUPPORTED_GRANT_TYPE -> ResponseCodesEnum.BAD_GRANT_TYPE;
        };
    }
}
