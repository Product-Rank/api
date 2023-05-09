package com.productrank.api.domain.service;

import com.productrank.api.domain.dto.RankingDto;
import com.productrank.api.domain.entity.Product;
import com.productrank.api.domain.entity.Ranking;
import com.productrank.api.domain.entity.User;
import com.productrank.api.domain.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RankingService {
    private final RankingRepository repository;

    @Transactional
    public RankingDto rank(RankingDto dto, User user, Product product) {
        Ranking rankEntity = Ranking.builder()
                .user(user)
                .product(product)
                .build();

        Ranking save = repository.save(rankEntity);
        return RankingDto.from(user.getId(), product.getId(), save);
    }
}
