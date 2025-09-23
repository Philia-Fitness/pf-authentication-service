package io.github.philiafitness.pfauthenticationservice.service;


import io.github.philiafitness.pfauthenticationservicedto.request.RefreshRequestBean;
import io.github.philiafitness.pfstarter.pfwebstarter.bean.response.BaseResponse;

public interface KeycloakRefreshGatewayWebClient {
    <T extends BaseResponse> T requestToken(RefreshRequestBean serviceRequest);

}
