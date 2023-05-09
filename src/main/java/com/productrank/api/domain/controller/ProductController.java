package com.productrank.api.domain.controller;

import com.productrank.api.config.security.custom.SecurityUser;
import com.productrank.api.domain.dto.CommentsDto;
import com.productrank.api.domain.dto.ProductDto;
import com.productrank.api.domain.dto.ProductEnrollReq;
import com.productrank.api.domain.entity.enums.RankingType;
import com.productrank.api.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return ResponseEntity.ok(productService.makeProduct(dto, req.companyId()));
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ProductDto> enrollComments(@AuthenticationPrincipal SecurityUser user, @RequestBody CommentsDto commentsDto, @PathVariable("productId")Long id) {
        return ResponseEntity.ok(productService.enrollComments(commentsDto, user.getUser().getId(), id));
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@AuthenticationPrincipal SecurityUser user, @RequestBody ProductDto dto) {
        return ResponseEntity.ok(productService.updateProduct(user.getUser(), dto));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@AuthenticationPrincipal SecurityUser user, @PathVariable("productId")Long id) {
        return ResponseEntity.ok(productService.voteUp(id, user.getUser()));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("productId")Long id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @GetMapping("/ranking/{rankingType}")
    public ResponseEntity<List<ProductDto>> getRankings(@PathVariable("rankingType")RankingType type){
        return ResponseEntity.ok(productService.getRankProducts(type));
    }
    /* TODO: 1. update product
                -1 view update (v)
                -2 vote update (v)
             2. update comments
                -1 like up
             3. delete product/comments
             4. favorite product 설정 (save)
             5. product 검색
             6. 페이지 조회
             7. 필터조건 (전체보기/주간베스트/월간베스트)
             8. 최근 업로드
             9. 이미지 url등록
     */


}
