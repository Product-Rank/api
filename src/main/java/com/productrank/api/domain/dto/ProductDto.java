package com.productrank.api.domain.dto;

import com.productrank.api.domain.entity.Comments;
import com.productrank.api.domain.entity.Product;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record ProductDto(
        String productName,
        String productDescription,
        String ThumbnailUrl,
        Long like,
        String company,
        List<String> imageUrls,
        List<CommentsDto> commentsList){
    public static ProductDto from(Product entity, String companyName) {
        return ProductDto.builder()
                .productName(entity.getProductName())
                .productDescription(entity.getProductDescription())
                .ThumbnailUrl(entity.getThumbnailUrl())
                .like(0L)
                .company(companyName)
                .build();
    }
    public static ProductDto from(Product entity) {
        return ProductDto.builder()
                .productName(entity.getProductName())
                .productDescription(entity.getProductDescription())
                .ThumbnailUrl(entity.getThumbnailUrl())
                .company(entity.getCompany().getCompanyName())
                .commentsList(entity.getCommentsList().stream().map(CommentsDto::from).collect(Collectors.toList()))
                .like(0L)
                .build();
    }
    public static ProductDto from(Product entity, List<CommentsDto> commentsList) {
        return ProductDto.builder()
                .productName(entity.getProductName())
                .productDescription(entity.getProductDescription())
                .ThumbnailUrl(entity.getThumbnailUrl())
                .company(entity.getCompany().getCompanyName())
                .like(0L)
                .commentsList(commentsList)
                .build();
    }
}
