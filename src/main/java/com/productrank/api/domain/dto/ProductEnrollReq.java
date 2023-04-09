package com.productrank.api.domain.dto;

public record ProductEnrollReq(String productName,
                               String productDescription,
                               Long companyId) {
}
