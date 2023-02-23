package com.productrank.api.domain.service;

import com.productrank.api.domain.dto.CommentsDto;
import com.productrank.api.domain.entity.Comments;
import com.productrank.api.domain.entity.Product;
import com.productrank.api.domain.entity.User;
import com.productrank.api.domain.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    public List<CommentsDto> productComments(Long productId){
        return commentsRepository.findByProductId(productId).stream()
                .map(CommentsDto::from).collect(Collectors.toList());
    }

    @Transactional
    public Comments  enrollComments(CommentsDto dto){
        Comments comments = Comments.builder()
                .comment(dto.comment())
                .like(0L)
                .parentsId(dto.parentsId())
                .build();
        return commentsRepository.save(comments);
    }

}
