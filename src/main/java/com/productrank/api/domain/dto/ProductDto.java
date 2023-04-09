package com.productrank.api.domain.dto;

import com.productrank.api.domain.entity.Comments;
import com.productrank.api.domain.entity.Product;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record ProductDto(
        Long id,
        String productName,
        String productDescription,
        String ThumbnailUrl,
        Long vote,
        String company,
        List<String> imageUrls,
        List<CommentsDto> commentsList){
    public static ProductDto from(Product entity, String companyName) {
        return ProductDto.builder()
                .id(entity.getId())
                .productName(entity.getProductName())
                .productDescription(entity.getProductDescription())
                .ThumbnailUrl(entity.getThumbnailUrl())
                .vote(entity.getVote())
                .company(companyName)
                .build();
    }
    public static ProductDto from(Product entity) {
        return ProductDto.builder()
                .id(entity.getId())
                .productName(entity.getProductName())
                .productDescription(entity.getProductDescription())
                .ThumbnailUrl(entity.getThumbnailUrl())
                .company(entity.getCompany().getCompanyName())
                .commentsList(entity.getCommentsList().stream().map(CommentsDto::from).collect(Collectors.toList()))
                .vote(entity.getVote())
                .build();
    }
    public static ProductDto from(Product entity, List<CommentsDto> commentsList) {
        return ProductDto.builder()
                .id(entity.getId())
                .productName(entity.getProductName())
                .productDescription(entity.getProductDescription())
                .ThumbnailUrl(entity.getThumbnailUrl())
                .company(entity.getCompany().getCompanyName())
                .vote(entity.getVote())
                .commentsList(commentsList)
                .build();
    }
}
