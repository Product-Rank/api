package com.productrank.api.domain.entity;

import com.productrank.api.domain.dto.CompanyDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "COMPANY")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String companyName;
    private String companyDescription;
    private String companyNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "company")
    private List<Product> productList;

    public void madeBy(User user){
        this.owner = user;
        user.addCompanyList(this);
    }

    @PrePersist
    public void prePersist() {
        this.productList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.productList.add(product);
    }

    public void updateData(CompanyDto dto){
        this.companyName = dto.companyName();
        this.companyDescription = dto.companyDescription();
        this.companyNumber = dto.companyNumber();
    }
}
