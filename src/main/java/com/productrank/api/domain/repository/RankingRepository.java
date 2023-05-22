package com.productrank.api.domain.repository;

import com.productrank.api.domain.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {
    @Query(nativeQuery = true,
            value = "select count(*) as counts, product_id as id from ranking " +
            "where created_at between :startDt and :endDt " +
            "group by product_id " +
            "limit :limit")
    List<RankingInterface> getRankingsInPeriod(String startDt, String endDt, int limit);
    @Query(nativeQuery = true, value = "select count(*) as counts, product_id as id from ranking " +
            "where created_at like '%'||:day||'%' " +
            "group by product_id " +
            "limit :limit")
    List<RankingInterface> getRankingsInPeriod(String day, int limit);
}
