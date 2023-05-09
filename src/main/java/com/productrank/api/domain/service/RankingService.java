package com.productrank.api.domain.service;

import com.productrank.api.domain.dto.RankingDto;
import com.productrank.api.domain.entity.Product;
import com.productrank.api.domain.entity.Ranking;
import com.productrank.api.domain.entity.User;
import com.productrank.api.domain.entity.enums.RankingType;
import com.productrank.api.domain.repository.RankingRepository;
import com.productrank.api.domain.repository.TestIf;
import com.productrank.api.domain.repository.TestRealIf;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RankingService {
    private final RankingRepository rankingRepository;

    @Transactional
    public RankingDto rank(RankingDto dto, User user, Product product) {
        Ranking rankEntity = Ranking.builder()
                .user(user)
                .product(product)
                .build();

        Ranking save = rankingRepository.save(rankEntity);
        return RankingDto.from(user.getId(), product.getId(), save);
    }

    public String getRanks(RankingType type) {
        List<String> sAndE = type.getStartAndEndDay();
        List<TestIf> rankingsInPeriod = rankingRepository.getRankingsInPeriod(sAndE.get(0), sAndE.get(1), 5);


        rankingsInPeriod.stream()
                .map(v -> new TestRealIf(v.getId(), v.getCounts()))
                .forEach(System.out::println);
        return "";
    }
}
