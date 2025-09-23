package io.github.philiafitness.pfauthenticationservicedto.response;

import io.github.philiafitness.pfstarter.pfwebstarter.bean.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class RefreshResponseBean extends BaseResponse {
    private String accessToken;
    private String tokenType;
    private Long expiresIn;
    private Long refreshExpiresIn;
    private String refreshToken;
}
