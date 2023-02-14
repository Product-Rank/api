package com.productrank.api.sns.kakao.client;

import com.productrank.api.domain.user.entity.SNSType;
import com.productrank.api.domain.user.entity.User;
import com.productrank.api.sns.google.dto.GoogleUser;
import com.productrank.api.sns.kakao.dto.KakaoUser;
import io.micrometer.common.util.StringUtils;
import lombok.Builder;

@Builder
public record UserDto(
        String email, SNSType snsType
        , String userName, String nickName,
        String picture) {
    public static UserDto from(User user){
        String picture = user.getPicture();
        if(StringUtils.isBlank(picture)){
            picture = ""; //:TODO defualt url 지정하기. (s3 경로)
        }
        return UserDto.builder()
                .email(user.getEmail())
                .snsType(user.getSnsType())
                .userName(user.getUserName())
                .picture(picture)
                .nickName(user.getNickName())
                .build();
    }

}
