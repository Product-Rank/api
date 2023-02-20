package com.productrank.api.domain.controller;

import com.productrank.api.config.security.custom.SecurityUser;
import com.productrank.api.domain.entity.Bookmark;
import com.productrank.api.domain.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/bookmark")
@Slf4j
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public List<Bookmark> findAll(@AuthenticationPrincipal SecurityUser userDetails) {
        log.info("USER:: {}",userDetails.getUser().getUserName());
        return bookmarkService.findAll();
    }
}
