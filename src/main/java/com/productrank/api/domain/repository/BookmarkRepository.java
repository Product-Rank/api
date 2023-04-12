package com.productrank.api.domain.repository;

import com.productrank.api.domain.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query(value = "select b from Bookmark b where b.user.id = :id")
    List<Bookmark> findByUserId(@Param("id") Long id);

}
