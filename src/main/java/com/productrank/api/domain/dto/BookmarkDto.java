package com.productrank.api.domain.dto;

import com.productrank.api.domain.entity.Bookmark;
import lombok.Builder;

@Builder
public record BookmarkDto(
        UserDto user, ProductDto productDto
) {
   public static BookmarkDto from(Bookmark bookmark){
       return BookmarkDto.builder()
               .user(UserDto.from(bookmark.getUser()))
               .productDto(ProductDto.from(bookmark.getProduct()))
               .build();
   }
}
