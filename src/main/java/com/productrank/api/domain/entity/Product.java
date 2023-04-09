package com.productrank.api.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends CommonEntity {

    private String productName;

    private String productDescription;

    private String ThumbnailUrl;

    @Column(name = "vote_cnt")
    private Long vote;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments> commentsList;

    @PrePersist
    public void prePersist() {
        vote = 0L;
        this.commentsList = new ArrayList<>();
    }

    public void setCompany(Company company){
        this.company = company;
        company.addProduct(this);
    }

    public void addComments(Comments comments) {
        this.commentsList.add(comments);
    }

    public void updateName(String productName){
        this.productName = productName;
    }

    public void updateProductDescription(String productDescription){
        this.productDescription = productDescription;
    }
    public void voteUp(){
        this.vote+=1;
    }
}
