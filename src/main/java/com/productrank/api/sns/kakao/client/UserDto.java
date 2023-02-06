package com.productrank.api.sns.kakao.client;

import com.productrank.api.domain.user.entity.SNSType;
import lombok.Builder;

@Builder
public record UserDto(
        String email, SNSType snsType
        , String userName, String nickName,
        String ThumbnailUrl) {
}
