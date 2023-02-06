package com.productrank.api.sns.kakao.dto;

import com.productrank.api.sns.common.SNSUser;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class KakaoUser extends SNSUser {
    private String nickname;
    private String email;
    private String thumbnail_image_url;
}
