package com.productrank.api.user.entity;

import com.productrank.api.company.entity.Company;
import jakarta.persistence.*;
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
