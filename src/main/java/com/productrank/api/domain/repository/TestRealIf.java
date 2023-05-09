package com.productrank.api.domain.repository;

public class TestRealIf implements TestIf{
    private Long id;
    private int counts;

    public TestRealIf(Long id, int counts) {
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
