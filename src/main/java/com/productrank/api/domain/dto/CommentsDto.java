package com.productrank.api.domain.dto;

import com.productrank.api.domain.entity.Comments;
import com.productrank.api.domain.entity.User;
import lombok.Builder;

@Builder
public record CommentsDto(
        Long id,
        Long like,
        String comment,
        Long parentsId,
        UserDto user,
        Long productId
) {
    public static CommentsDto from(Comments comments){
        User user = comments.getUser();
        return CommentsDto.builder()
                .id(comments.getId())
                .like(comments.getLike())
                .comment(comments.getComment())
                .parentsId(comments.getParentsId())
                .user(UserDto.builder()
                        .nickName(user.getNickName())
                        .email(user.getEmail())
                        .build())
                .productId(comments.getProduct().getId())
                .build();
    }
}
