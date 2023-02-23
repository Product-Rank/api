package com.productrank.api.domain.repository;

import com.productrank.api.domain.entity.enums.SNSType;
import com.productrank.api.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = false, value = "select u from User u where u.email = :email and u.snsType = :snsType")
    Optional<User> findByEmailAndSnsType(@Param("email") String email, @Param("email") SNSType snsType);
    @Query(nativeQuery = false, value = "select u from User u where u.email = :email")
    Optional<User> findByEmail(@Param("email")String email);
}