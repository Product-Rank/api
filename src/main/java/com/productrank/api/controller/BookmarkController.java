package com.productrank.api.controller;

import com.productrank.api.entity.Bookmark;
import com.productrank.api.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/bookmark")
@ResponseBody
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public List<Bookmark> findAll() {
        return bookmarkService.findAll();
    }
}
