package com.productrank.api.domain.service;

import com.productrank.api.domain.dto.BookmarkDto;
import com.productrank.api.domain.dto.ProductDto;
import com.productrank.api.domain.entity.Bookmark;
import com.productrank.api.domain.entity.Product;
import com.productrank.api.domain.entity.User;
import com.productrank.api.domain.repository.BookmarkRepository;
import com.productrank.api.domain.repository.ProductRepository;
import com.productrank.api.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final ProductRepository productRepository;
    public List<ProductDto> getMyFavorite(User user) {
        return bookmarkRepository.findByUserId(user.getId())
                .stream().map(BookmarkDto::from)
                .map(BookmarkDto::productDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public ProductDto enrollBookmark(User user, Long productId) {
        Product product = productRepository.findByProductId(productId).orElseThrow(
                () -> new RuntimeException(ErrorCode.NOT_EXIST_PRODUCT.getMessage())
        );

        Bookmark bookmark = Bookmark.builder()
                .user(user)
                .product(product)
                .build();

        if(bookmarkRepository.exists(Example.of(bookmark))){
            return BookmarkDto.from(bookmark).productDto();
        }

        Bookmark saveEntity = bookmarkRepository.save(bookmark);

        return BookmarkDto.from(saveEntity).productDto();
    }
}
