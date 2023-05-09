package com.productrank.api.domain.dto;

import com.productrank.api.domain.entity.Ranking;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RankingDto(
        Long productId,
        Long userId,
        LocalDateTime createdAt
) {
    public static RankingDto from(Long userId, Long productId, Ranking entity){
        return RankingDto.builder()
                .productId(productId)
                .userId(userId)
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
