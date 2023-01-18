package com.productrank.api.domain.bookmark.controller;

import com.productrank.api.domain.bookmark.entity.Bookmark;
import com.productrank.api.domain.bookmark.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/bookmark")
@ResponseBody
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public List<Bookmark> findAll() {
        Bookmark bookmark = new Bookmark();
        System.out.println("bookmark = " + bookmark);
        List<Bookmark> all = bookmarkService.findAll();
        System.out.println(all.get(0).getId());
        return all;
    }
}
