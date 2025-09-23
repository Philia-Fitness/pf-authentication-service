package io.github.philiafitness.pfauthenticationservice.service;


import io.github.philiafitness.pfauthenticationservicedto.request.LoginRequestBean;
import io.github.philiafitness.pfstarter.pfwebstarter.bean.response.BaseResponse;

public interface KeycloakLoginGatewayWebClient {
    <T extends BaseResponse> T requestToken(LoginRequestBean serviceRequest);

}
