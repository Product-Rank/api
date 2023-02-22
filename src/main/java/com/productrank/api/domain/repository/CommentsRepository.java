package com.productrank.api.domain.repository;

import com.productrank.api.domain.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Query(value = "select c from Comments c " +
            "left join fetch Product p " +
            "left join fetch User u " +
            "where p.id = :productId")
    List<Comments> findByProductId(Long productId);
}