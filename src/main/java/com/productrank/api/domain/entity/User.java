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
    @Column(unique = true)
    private String email;
    @Enumerated(value = EnumType.STRING)
    private SNSType snsType;
    private String userName;
    private String nickName;
    private String picture;
    private String accessToken;
    @OneToMany(mappedBy = "owner")
    List<Company> companyList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comments> commentsList;
    @PrePersist
    public void prePersist() {
        this.companyList = new ArrayList<>();
        this.commentsList = new ArrayList<>();
    }

    public void addCompanyList(Company company) {
        this.companyList.add(company);
    }

    public void addComments(Comments comments) {
        this.commentsList.add(comments);
    }
}
