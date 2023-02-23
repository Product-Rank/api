package com.productrank.api.domain.repository;

import com.productrank.api.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select p from Product p " +
            "left join fetch Comments " +
            "where p.id = :productId")
    Optional<Product> findByProductId(Long productId);
}