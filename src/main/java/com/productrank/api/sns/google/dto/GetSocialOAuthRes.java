package com.productrank.api.sns.google.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor@Builder
public class GetSocialOAuthRes {

    private String jwtToken;
    private int user_num;
    private String accessToken;
    private String tokenType;
}

