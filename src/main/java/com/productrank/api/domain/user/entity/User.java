package com.productrank.api.domain.user.entity;

import com.productrank.api.domain.company.entity.Company;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "PRODUCT_USER")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    @Enumerated(value = EnumType.STRING)
    private SNSType snsType;

    private String userName;
    private String nickName;
    private String ThumbnailUrl;

    @OneToMany(mappedBy = "owner")
    List<Company> companyList = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.companyList = new ArrayList<>();
    }
}
