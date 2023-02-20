package com.productrank.api.domain.dto;

import com.productrank.api.domain.entity.Company;
import com.productrank.api.domain.entity.User;
import jakarta.persistence.criteria.From;
import lombok.Builder;
import lombok.Setter;

@Builder
public record CompanyDto(String companyName,
                         String companyDescription,
                         String companyNumber, String ownerName
                         ) {
    public static  CompanyDto from(Company company){
        return CompanyDto.builder()
                .companyDescription(company.getCompanyDescription())
                .companyName(company.getCompanyName())
                .companyNumber(company.getCompanyNumber())
                .ownerName(company.getOwner().getUserName())
                .build();
    }
}
