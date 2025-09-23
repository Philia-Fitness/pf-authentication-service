package io.github.philiafitness.pfauthenticationservice.service;


import io.github.philiafitness.pfauthenticationservicedto.request.LogoutRequestBean;
import io.github.philiafitness.pfstarter.pfwebstarter.bean.response.BaseResponse;

public interface KeycloakLogoutGatewayWebClient {
    <T extends BaseResponse> T requestLogout(LogoutRequestBean serviceRequest);
}
