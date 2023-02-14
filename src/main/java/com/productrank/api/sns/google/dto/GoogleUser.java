package com.productrank.api.sns.google.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.productrank.api.domain.user.entity.SNSType;
import com.productrank.api.sns.common.SNSUser;
import com.productrank.api.sns.kakao.client.UserDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleUser extends SNSUser {
    private String name;
    private String email;
    private String picture;
    public final SNSType snsType = SNSType.GOOGLE;
}

