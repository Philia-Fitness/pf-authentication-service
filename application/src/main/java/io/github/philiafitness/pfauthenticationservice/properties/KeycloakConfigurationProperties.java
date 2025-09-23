package io.github.philiafitness.pfauthenticationservice.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "com.newhorizon.nh-authentication-support-service.keycloak")
public class KeycloakConfigurationProperties {
    private Client client = new Client();
    private Endpoints endpoints = new Endpoints();

    @Getter
    @Setter
    public static class Client {
        private String id;
        private String secret;
    }

    @Getter
    @Setter
    public static class Endpoints {
        private String authentication;
        private String refreshToken;
        private String logout;
    }
}
