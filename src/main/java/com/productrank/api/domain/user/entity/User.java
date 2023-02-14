package com.productrank.api.domain.user.entity;

import com.productrank.api.domain.CommonEntity;
import com.productrank.api.domain.company.entity.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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
    @OneToMany(mappedBy = "owner")
    List<Company> companyList;
    @PrePersist
    public void prePersist() {
        this.companyList = new ArrayList<>();
    }
}
