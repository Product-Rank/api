package com.productrank.api.product.entity;

import com.productrank.api.company.entity.Company;
import com.productrank.api.user.entity.SNSType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Table(name = "PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String productDescription;
    private String ThumbnailUrl;
    @Column(name = "like_cnt")
    private Long like;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @PrePersist
    public void prePersist() {
        like = 0L;
    }
}
