package com.productrank.api.domain.controller;

import com.productrank.api.config.security.custom.SecurityUser;
import com.productrank.api.domain.dto.CommentsDto;
import com.productrank.api.domain.dto.ProductDto;
import com.productrank.api.domain.dto.ProductEnrollReq;
import com.productrank.api.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllList() {
        return ResponseEntity.ok(productService.getAllProductList());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getDetails(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productService.getDetailsProduct(productId));
    }

    @PostMapping
    public ResponseEntity<ProductDto> enrollProduct(@RequestBody ProductEnrollReq req) {
        ProductDto dto = ProductDto.builder()
                .productName(req.productName())
                .productDescription(req.productDescription())
                .build();
        return ResponseEntity.ok(productService.makeProduct(dto, req.companyName()));
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ProductDto> enrollComments(@AuthenticationPrincipal SecurityUser user, @RequestBody CommentsDto commentsDto, @PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productService.enrollComments(commentsDto, user.getUser().getId(), productId));
    }
}
