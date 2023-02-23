package com.productrank.api.domain.service;

import com.productrank.api.domain.entity.Bookmark;
import com.productrank.api.domain.repository.BookmarkRepository;
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
