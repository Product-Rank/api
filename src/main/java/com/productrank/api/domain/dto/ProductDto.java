package com.productrank.api.domain.dto;

import java.util.List;

public record ProductDto(
        String productName,
        String productDescription,
        String ThumbnailUrl,
        Long like,
        String company,
        List<String> imageUrls){
}
