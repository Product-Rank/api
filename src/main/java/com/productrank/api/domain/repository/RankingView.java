package com.productrank.api.domain.repository;

public class RankingView implements RankingInterface {
    private Long id;
    private int counts;

    public RankingView(Long id, int counts) {
        this.id = id;
        this.counts = counts;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public int getCounts() {
        return this.counts;
    }
}
