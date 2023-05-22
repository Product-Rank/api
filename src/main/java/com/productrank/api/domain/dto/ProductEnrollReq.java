package com.productrank.api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record ProductEnrollReq(@NotBlank String productName,
                               String productDescription,
                               @NotBlank Long companyId) {
}
