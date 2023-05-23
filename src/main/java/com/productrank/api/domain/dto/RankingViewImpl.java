package com.productrank.api.domain.dto;

public class RankingViewImpl implements RankingView {
    private Long id;
    private Integer count;

    public RankingViewImpl(Long id, Integer count) {
        this.id = id;
        this.count = count;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public Integer getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return "RankingViewImpl{" +
                "id=" + id +
                ", counts=" + count +
                '}';
    }
}
