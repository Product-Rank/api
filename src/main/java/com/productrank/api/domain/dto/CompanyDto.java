package com.productrank.api.domain.dto;

import com.productrank.api.domain.entity.Company;
import com.productrank.api.domain.entity.User;
import jakarta.persistence.criteria.From;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Setter;

@Builder
public record CompanyDto(@NotBlank String companyName,
                         String companyDescription,
                         @NotBlank  String companyNumber, String ownerName, Long id
                         ) {
    public static  CompanyDto from(Company company){
        return CompanyDto.builder()
                .id(company.getId())
                .companyDescription(company.getCompanyDescription())
                .companyName(company.getCompanyName())
                .companyNumber(company.getCompanyNumber())
                .ownerName(company.getOwner().getUserName())
                .build();
    }
}
