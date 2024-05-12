package com.example.cuk_food.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@NoArgsConstructor
@Getter
@Entity
public class Review  extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    //리뷰 내용
    @Setter
    private String context;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    //이후, 회원 엔티티와 연동 필요 ...

}
