package com.productrank.api.domain.entity;

import com.productrank.api.domain.entity.enums.SNSType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "PRODUCT_USER")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends CommonEntity {
    private String email;
    @Enumerated(value = EnumType.STRING)
    private SNSType snsType;
    private String userName;
    private String nickName;
    private String picture;
    private String accessToken;
    @OneToMany(mappedBy = "owner")
    List<Company> companyList;
    @PrePersist
    public void prePersist() {
        this.companyList = new ArrayList<>();
    }

    public void addCompanyList(Company company) {
        this.companyList.add(company);
    }
}
