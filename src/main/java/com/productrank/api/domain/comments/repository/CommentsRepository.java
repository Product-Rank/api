package com.productrank.api.domain.comments.repository;

import com.productrank.api.domain.comments.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
}