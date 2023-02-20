package com.productrank.api.domain.repository;

import com.productrank.api.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(value = "select c from Company c join fetch User u where u.id = :id")
    List<Company> findByUserId(Long id);
}