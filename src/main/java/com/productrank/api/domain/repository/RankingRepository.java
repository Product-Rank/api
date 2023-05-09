package com.productrank.api.domain.repository;

import com.productrank.api.domain.entity.Ranking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {
    @Query(nativeQuery = true,
            value = "select count(*) as count, product_id as id from ranking " +
            "where created_at between :startDt and :endDt " +
            "group by product_id " +
            "limit :limit")
    List<TestIf> getRankingsInPeriod(String startDt, String endDt, int limit);
//    @Query(nativeQuery = true, value = "select count(*), product_id from ranking " +
//            "where created_ad like :startDt||'%' " +
//            "group by product_id " +
//            "limit :limit")
//    List<TestDto> getRankingsInPeriod(String day, int limit);
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    class TestDto{
        private int counts;
        private Long productId;
    }
}
