package com.productrank.api.domain.bookmark.service;

import com.productrank.api.domain.bookmark.entity.Bookmark;
import com.productrank.api.domain.bookmark.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public List<Bookmark> findAll() {
        List<Bookmark> all = bookmarkRepository.findAll();
        return all;
    }
}
