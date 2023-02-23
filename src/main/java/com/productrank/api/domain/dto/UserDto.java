package com.productrank.api.domain.dto;

import com.productrank.api.domain.entity.enums.SNSType;
import com.productrank.api.domain.entity.User;
import io.micrometer.common.util.StringUtils;
import lombok.Builder;

@Builder
public record UserDto(
        String email, SNSType snsType
        , String userName, String nickName,
        String picture, String accessToken) {
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
                .accessToken(user.getAccessToken())
                .build();
    }

}
