package com.productrank.api.sns.kakao.dto;

import com.productrank.api.domain.user.entity.SNSType;
import com.productrank.api.sns.common.SNSUser;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class KakaoUser extends SNSUser {
    private String name;
    private String email;
    private String picture;
    public final SNSType snsType = SNSType.KAKAO;
}
