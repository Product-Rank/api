package com.productrank.api.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "COMMENTS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comments extends CommonEntity {

    private String comment;
    @Column(name = "like_cnt")
    private Long like;

    @Column(nullable = true)
    private Long parentsId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @PrePersist
    public void prePersist() {
        like = 0L;
    }

    public void setProduct(Product product){
        this.product = product;
        product.addComments(this);
    }

    public void setUser(User user){
        this.user = user;
        user.addComments(this);
    }
}
