package io.github.philiafitness.pfauthenticationservice.controller;

import com.newhorizon.nhauthenticationsupportservice.service.KeycloakLoginGatewayWebClient;
import com.newhorizon.nhauthenticationsupportservice.service.KeycloakLogoutGatewayWebClient;
import com.newhorizon.nhauthenticationsupportservice.service.KeycloakRefreshGatewayWebClient;
import com.newhorizon.nhauthenticationsupportservicedto.monolith.BaseResponse;
import com.newhorizon.nhauthenticationsupportservicedto.request.LoginRequestBean;
import com.newhorizon.nhauthenticationsupportservicedto.request.LogoutRequestBean;
import com.newhorizon.nhauthenticationsupportservicedto.request.RefreshRequestBean;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationControllerImpl extends BaseController {
    private final KeycloakLoginGatewayWebClient loginClient;
    private final KeycloakLogoutGatewayWebClient logoutClient;
    private final KeycloakRefreshGatewayWebClient refreshClient;

    @PostMapping("/user/token")
    public ResponseEntity<? extends BaseResponse> login(@Valid @RequestBody LoginRequestBean requestBean) {
        return handlerResponse(loginClient.requestToken(requestBean));
    }

    @DeleteMapping("/user/token")
    public ResponseEntity<? extends BaseResponse> logout(@Valid @RequestBody LogoutRequestBean requestBean) {
        return handlerResponse(logoutClient.requestLogout(requestBean));

    }

    @PutMapping("/user/token")
    public ResponseEntity<? extends BaseResponse> refresh(@Valid @RequestBody RefreshRequestBean requestBean) {
        return handlerResponse(refreshClient.requestToken(requestBean));

    }
}
