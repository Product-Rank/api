package com.productrank.api.domain.controller;

import com.productrank.api.config.security.custom.SecurityUser;
import com.productrank.api.domain.dto.BookmarkDto;
import com.productrank.api.domain.dto.ProductDto;
import com.productrank.api.domain.entity.Bookmark;
import com.productrank.api.domain.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/bookmark")
@Slf4j
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public ResponseEntity<List<ProductDto>>  findAll(@AuthenticationPrincipal SecurityUser sUser) {
        return ResponseEntity.ok(bookmarkService.getMyFavorite(sUser.getUser()));
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ProductDto> enrollBookmark(@AuthenticationPrincipal SecurityUser user, @PathVariable("productId")Long productId){
        return ResponseEntity.ok(bookmarkService.enrollBookmark(user.getUser(), productId));
    }
}
